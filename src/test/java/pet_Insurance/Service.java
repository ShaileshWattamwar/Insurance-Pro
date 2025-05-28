package pet_Insurance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;
import pet_Insurance.enums.PetType;
import pet_Insurance.repository.PetRepository;
import pet_Insurance.service.serviceImp.PetServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private PetRequest petRequest;
    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        petRequest = PetRequest.builder()
                .name("Buddy")
                .breed("Labrador")
                .type(PetType.DOG)
                .age(5)
                .ownerId("owner123")
                .build();

        pet = Pet.builder()
                .id("pet123")
                .name("Buddy")
                .breed("Labrador")
                .type(PetType.DOG)
                .age(5)
                .ownerId("owner123")
                .build();
    }

    @Test
    void testAddPet() {
        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        Pet saved = petService.addPet(petRequest);
        assertNotNull(saved);
        assertEquals("Buddy", saved.getName());
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void testGetPetById() {
        when(petRepository.findById("pet123")).thenReturn(Optional.of(pet));
        Pet found = petService.getPetById("pet123");
        assertNotNull(found);
        assertEquals("Buddy", found.getName());
    }

    @Test
    void testGetAllPets() {
        when(petRepository.findAll()).thenReturn(Arrays.asList(pet));
        List<Pet> pets = petService.getAllPets();
        assertEquals(1, pets.size());
    }

    @Test
    void testUpdatePet() {
        PetRequest updatedRequest = PetRequest.builder()
                .name("Max")
                .breed("Beagle")
                .type(PetType.DOG)
                .age(6)
                .ownerId("owner123")
                .build();

        Pet updatedPet = Pet.builder()
                .id("pet123")
                .name("Max")
                .breed("Beagle")
                .type(PetType.DOG)
                .age(6)
                .ownerId("owner123")
                .build();

        when(petRepository.findById("pet123")).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        Pet result = petService.updatePet("pet123", updatedRequest);
        assertNotNull(result);
        assertEquals("Max", result.getName());
        assertEquals("Beagle", result.getBreed());
    }

    @Test
    void testDeletePet() {
        doNothing().when(petRepository).deleteById("pet123");
        petService.deletePet("pet123");
        verify(petRepository, times(1)).deleteById("pet123");
    }
}
