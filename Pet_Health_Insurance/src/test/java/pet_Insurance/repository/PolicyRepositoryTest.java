package pet_Insurance.repository;

import org.springframework.boot.test.context.SpringBootTest;
import pet_Insurance.entities.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PolicyRepositoryTest {

    @Autowired
    private PolicyRepository policyRepository;

    @BeforeEach
    void cleanDatabase() {
        policyRepository.deleteAll();  // ✨ This fixes test contamination
    }

    @Test
    void testSavePolicy() {
        Policy policy = Policy.builder()
                .petId("PET001")
                .premiumAmountInINR(6000.00)
                .isClaimed(false)
                .build();

        Policy saved = policyRepository.save(policy);

        assertNotNull(saved.getId());
        assertEquals("PET001", saved.getPetId());
        assertEquals(6000.00, saved.getPremiumAmountInINR());
    }

    @Test
    void testFindById() {
        Policy policy = Policy.builder()
                .petId("PET002")
                .premiumAmountInINR(10000.00)
                .isClaimed(true)
                .build();

        Policy saved = policyRepository.save(policy);

        Optional<Policy> result = policyRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("PET002", result.get().getPetId());
        assertTrue(result.get().isClaimed());
    }

    @Test
    void testFindAll() {
        policyRepository.save(Policy.builder().petId("PET003").premiumAmountInINR(3000).isClaimed(false).build());
        policyRepository.save(Policy.builder().petId("PET004").premiumAmountInINR(8000).isClaimed(true).build());

        List<Policy> all = policyRepository.findAll();

        assertEquals(2, all.size());
    }

    @Test
    void testDeleteById() {
        Policy policy = policyRepository.save(Policy.builder()
                .petId("PET005")
                .premiumAmountInINR(15000)
                .isClaimed(false)
                .build());

        String id = policy.getId();
        policyRepository.deleteById(id);

        Optional<Policy> result = policyRepository.findById(id);
        assertFalse(result.isPresent());
    }
}
