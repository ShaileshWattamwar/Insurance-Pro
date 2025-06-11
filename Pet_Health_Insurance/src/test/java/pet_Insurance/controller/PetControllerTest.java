package pet_Insurance.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pet_Insurance.controllers.PetController;
import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;
import pet_Insurance.enums.PetType;
import pet_Insurance.service.PetService;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PetControllerTest {

    private PetService petService;
    private PetController petController;

    @BeforeEach
    public void setup() {
        petService = mock(PetService.class);
        petController = new PetController(petService);
    }

    @Test
    public void testAddPet() {
        PetRequest request = new PetRequest("Rocky", PetType.DOG, "Lab", 3);
        Pet pet = new Pet("1", "Buddy", PetType.DOG, "Lab", 3);

        when(petService.addPet(request)).thenReturn(pet);
        Pet result = petController.addPet(request);
        assertNotNull(result);
        assertEquals(PetType.DOG, result.getType());
        verify(petService).addPet(request);
    }


    @Test
    public void testGetAllPets() {
        List<Pet> pets = Arrays.asList(
                new Pet("1", "Rocky", PetType.DOG, "Desi", 8),
                new Pet("2", "Mili", PetType.CAT, "African", 4)
            );

        when(petService.getAllPets()).thenReturn(pets);
        List<Pet> result = petController.getAllPets();
        assertEquals(2, result.size());
        verify(petService).getAllPets();
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet("1", "Rocky", PetType.DOG, "German", 3);

        when(petService.getPetById("1")).thenReturn(pet);
        Pet result = petController.getPetById("1");
        assertEquals("German", result.getBreed());
        assertEquals(PetType.DOG, result.getType());
        assertEquals("Rocky", result.getName());
        verify(petService).getPetById("1");
    }

    @Test
    public void testUpdatePet() {
        PetRequest request = new PetRequest("Khushi", PetType.CAT, "Desi", 5);  // proper constructor
        Pet updatedPet = new Pet("1", "Khushi", PetType.CAT, "Desi", 5);        // proper values

        when(petService.updatePet("1", request)).thenReturn(updatedPet);
        Pet result = petController.updatePet("1", request);
        assertEquals("Desi", result.getBreed());
        assertEquals(PetType.CAT, result.getType());
        assertEquals("Khushi", result.getName());
        verify(petService).updatePet("1", request);
    }


    @Test
    public void testDeletePet() {
        doNothing().when(petService).deletePet("1");
        petController.deletePet("1");
        verify(petService).deletePet("1");
    }
}
