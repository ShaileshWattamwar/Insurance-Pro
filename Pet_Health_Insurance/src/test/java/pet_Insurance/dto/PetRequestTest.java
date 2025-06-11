package pet_Insurance.dto;

import org.junit.jupiter.api.Test;
import pet_Insurance.enums.PetType;

import static org.assertj.core.api.Assertions.assertThat;

public class PetRequestTest {


    @Test
    void testAllArgsConstructorAndGetters() {
        PetRequest pet = new PetRequest("Buddy", PetType.DOG, "Beagle", 3);

        assertThat(pet.getName()).isEqualTo("Buddy");
        assertThat(pet.getType()).isEqualTo(PetType.DOG);
        assertThat(pet.getBreed()).isEqualTo("Beagle");
        assertThat(pet.getAge()).isEqualTo(3);
    }

    @Test
    void testNoArgsConstructorAndSettersGetters() {
        PetRequest pet = new PetRequest();
        pet.setName("Buddy");
        pet.setType(PetType.DOG);
        pet.setBreed("Labrador");
        pet.setAge(4);

        assertThat(pet.getName()).isEqualTo("Buddy");
        assertThat(pet.getType()).isEqualTo(PetType.DOG);
        assertThat(pet.getBreed()).isEqualTo("Labrador");
        assertThat(pet.getAge()).isEqualTo(4);
    }

    @Test
    void testAllArgsConstructor() {
        PetRequest pet = new PetRequest("Milo", PetType.CAT, "Persian", 2);

        assertThat(pet.getName()).isEqualTo("Milo");
        assertThat(pet.getType()).isEqualTo(PetType.CAT);
        assertThat(pet.getBreed()).isEqualTo("Persian");
        assertThat(pet.getAge()).isEqualTo(2);
    }

    @Test
    void testToStringNotNullOrEmpty() {
        PetRequest pet = new PetRequest("Rocky", PetType.DOG, "Beagle", 3);
        String output = pet.toString();

        assertThat(output).isNotNull().isNotEmpty();
        assertThat(output).contains("Rocky", "DOG", "Beagle", "3");
    }


    @Test
    void testCanEqual() {
        PetRequest pet = new PetRequest("Simba", PetType.DOG, "Bulldog", 6);
        assertThat(pet.canEqual(new PetRequest())).isTrue();
        assertThat(pet.canEqual("not a pet")).isFalse();
    }

    @Test
    void testEqualsAndHashCode() {
        PetRequest pet1 = new PetRequest("Buddy", PetType.DOG, "Beagle", 3);
        PetRequest pet2 = new PetRequest("Buddy", PetType.DOG, "Beagle", 3);
        PetRequest pet3 = new PetRequest("Max", PetType.CAT, "Siamese", 2);

        // Reflexive
        assertThat(pet1).isEqualTo(pet1);
        // Symmetric and same values
        assertThat(pet1).isEqualTo(pet2);
        assertThat(pet2).isEqualTo(pet1);
        // Unequal objects
        assertThat(pet1).isNotEqualTo(pet3);
        // Null and different class checks
        assertThat(pet1).isNotEqualTo(null);
        assertThat(pet1).isNotEqualTo("some string");

        // Consistent hashCode
        assertThat(pet1.hashCode()).isEqualTo(pet2.hashCode());
    }
}
