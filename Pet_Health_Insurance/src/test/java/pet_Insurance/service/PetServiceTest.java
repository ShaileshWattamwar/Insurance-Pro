package pet_Insurance.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;
import pet_Insurance.enums.PetType;
import pet_Insurance.repository.PetRepository;
import pet_Insurance.service.serviceImp.PetServiceImpl;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetServiceImpl petService;

    Pet pet;
    PetRequest request;

    @BeforeEach
    void init() {
        request = new PetRequest("Buddy", PetType.DOG, "Labrador", 5);
        pet = Pet.builder()
                .id("123")
                .name("Buddy")
                .type(PetType.DOG)
                .breed("Labrador")
                .age(5)
                .build();
    }

    @Test
    void addPet_callsSave() {
        when(petRepository.save(any())).thenReturn(pet);

        Pet saved = petService.addPet(request);

        assertNotNull(saved);
        assertEquals("Buddy", saved.getName());
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void getAllPets_returnsPets() {
        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<Pet> pets = petService.getAllPets();

        assertNotNull(pets);
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
        verify(petRepository).findAll();
    }

    @Test
    void getPetById_foundAndNotFound() {
        when(petRepository.findById("123")).thenReturn(Optional.of(pet));
        when(petRepository.findById("999")).thenReturn(Optional.empty());

        Pet found = petService.getPetById("123");
        assertNotNull(found);
        assertEquals("Buddy", found.getName());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> petService.getPetById("999"));
        assertTrue(ex.getMessage().contains("Pet not found with the provided id"));
    }

    @Test
    void updatePet_updatesAndSaves() {
        PetRequest updateRequest = new PetRequest("Max", PetType.DOG, "Beagle", 7);

        when(petRepository.findById("123")).thenReturn(Optional.of(pet));
        when(petRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Pet updated = petService.updatePet("123", updateRequest);

        assertEquals("Max", updated.getName());
        assertEquals("Beagle", updated.getBreed());
        assertEquals(7, updated.getAge());
        verify(petRepository).findById("123");
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void deletePet_deletesById() {
        doNothing().when(petRepository).deleteById("123");
        petService.deletePet("123");
        verify(petRepository).deleteById("123");
    }
}
