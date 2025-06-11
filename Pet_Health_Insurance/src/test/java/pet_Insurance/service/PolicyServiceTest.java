package pet_Insurance.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pet_Insurance.dto.PolicyResponse;
import pet_Insurance.entities.Pet;
import pet_Insurance.entities.Policy;
import pet_Insurance.enums.PetType;
import pet_Insurance.repository.PetRepository;
import pet_Insurance.repository.PolicyRepository;
import pet_Insurance.service.serviceImp.PolicyServiceImpl;

public class PolicyServiceTest {

    private PetRepository petRepository;
    private PolicyRepository policyRepository;
    private PolicyServiceImpl policyService;

    @BeforeEach
    void setup() {
        petRepository = mock(PetRepository.class);
        policyRepository = mock(PolicyRepository.class);
        policyService = new PolicyServiceImpl(petRepository, policyRepository);
    }

    @Test
    void testApplyPolicy_success() {
        Pet pet = Pet.builder()
                .id("pet123")
                .type(PetType.DOG)
                .breed("German Shepherd")
                .age(3)
                .build();

        when(petRepository.findById("pet123")).thenReturn(Optional.of(pet));

        when(policyRepository.save(any(Policy.class)))
                .thenAnswer(invocation -> {
                    Policy input = invocation.getArgument(0);
                    return input.toBuilder().id("policy123").build(); // Set ID here
                });

        PolicyResponse response = policyService.applyPolicy("pet123");

        assertNotNull(response);
        assertEquals("policy123", response.getPolicyId()); // Updated to match mocked ID
        assertTrue(response.getPremiumAmountInINR() > 0);
        assertFalse(response.isClaimed());

        // Verify interactions
        verify(petRepository).findById("pet123");
        verify(policyRepository).save(any(Policy.class));
    }

    @Test
    void testApplyPolicy_petNotFound() {
        when(petRepository.findById("invalidPet")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            policyService.applyPolicy("invalidPet");
        });
        assertEquals("Pet not found", ex.getMessage());

        verify(petRepository).findById("invalidPet");
        verify(policyRepository, never()).save(any());
    }

    @Test
    void testClaimPolicy_success() {
        Policy policy = Policy.builder()
                .id("policy123")
                .petId("pet123")
                .premiumAmountInINR(1000)
                .isClaimed(false)
                .build();

        when(policyRepository.findById("policy123")).thenReturn(Optional.of(policy));
        when(policyRepository.save(any(Policy.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PolicyResponse response = policyService.claimPolicy("policy123");

        assertNotNull(response);
        assertEquals("policy123", response.getPolicyId());
        assertEquals(1000, response.getPremiumAmountInINR());
        assertTrue(response.isClaimed());

        verify(policyRepository).findById("policy123");
        verify(policyRepository).save(any(Policy.class));
    }

    @Test
    void testClaimPolicy_policyNotFound() {
        when(policyRepository.findById("invalidPolicy")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            policyService.claimPolicy("invalidPolicy");
        });
        assertEquals("Policy not found", ex.getMessage());

        verify(policyRepository).findById("invalidPolicy");
        verify(policyRepository, never()).save(any());
    }

    @Test
    void testCalculatePremium_variousCases() throws Exception {
        // Use reflection to access private method for direct testing
        var method = PolicyServiceImpl.class.getDeclaredMethod("calculatePremium", PetType.class, String.class, int.class);
        method.setAccessible(true);

        // DOG, German Shepherd, age 1 (young)
        double premium = (double) method.invoke(policyService, PetType.DOG, "German Shepherd", 1);
        assertEquals(100.0 * 1.5 * 1.0 * 83.0, premium, 0.001);

        // CAT, Persian, age 5 (middle)
        premium = (double) method.invoke(policyService, PetType.CAT, "Persian", 5);
        assertEquals(70.0 * 1.3 * 1.2 * 83.0, premium, 0.001);

        // HORSE, Arabian, age 7 (older)
        premium = (double) method.invoke(policyService, PetType.HORSE, "Arabian", 7);
        assertEquals(150.0 * 1.1 * 1.5 * 83.0, premium, 0.001);

        // DOG, unknown breed, age 3 (middle)
        premium = (double) method.invoke(policyService, PetType.DOG, "Unknown", 3);
        assertEquals(100.0 * 1.0 * 1.2 * 83.0, premium, 0.001);
    }
}
