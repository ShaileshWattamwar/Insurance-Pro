package pet_Insurance.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import pet_Insurance.entities.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pet_Insurance.enums.PetType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    void cleanDatabase() {
        petRepository.deleteAll();
    }

    @Test
    void testSavePet() {
        Pet pet = Pet.builder()
                .name("KillBill")  // Fixed casing
                .type(PetType.valueOf("DOG"))
                .breed("Indian")
                .age(4)
                .build();

        Pet saved = petRepository.save(pet);

        assertNotNull(saved.getId());
        assertEquals("KillBill", saved.getName());
    }

    @Test
    void testFindById() {
        Pet pet = Pet.builder()
                .name("Mini")
                .type(PetType.valueOf("CAT"))
                .breed("Persian")
                .age(2)
                .build();

        Pet saved = petRepository.save(pet);

        Optional<Pet> result = petRepository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Mini", result.get().getName());
    }

    @Test
    void testFindAll() {
        petRepository.save(Pet.builder().name("patty").type(PetType.valueOf("DOG")).breed("Husky").age(5).build());
        petRepository.save(Pet.builder().name("Luna").type(PetType.valueOf("CAT")).breed("Persian").age(3).build());

        List<Pet> allPets = petRepository.findAll();

        assertEquals(2, allPets.size());  // Now this will pass
    }

    @Test
    void testDeleteById() {
        Pet pet = petRepository.save(Pet.builder()
                .name("patty")
                .type(PetType.valueOf("DOG"))
                .breed("Bulldog")
                .age(6)
                .build());

        String id = pet.getId();
        petRepository.deleteById(id);

        Optional<Pet> deleted = petRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}
