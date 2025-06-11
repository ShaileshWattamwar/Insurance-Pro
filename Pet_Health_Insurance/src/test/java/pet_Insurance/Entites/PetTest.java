package pet_Insurance.Entites;

import org.junit.jupiter.api.Test;
import pet_Insurance.entities.Pet;
import pet_Insurance.enums.PetType;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {

    @Test
    void testAllArgsConstructor() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);

        assertThat(pet.getId()).isEqualTo("1");
        assertThat(pet.getName()).isEqualTo("Max");
        assertThat(pet.getType()).isEqualTo(PetType.DOG);
        assertThat(pet.getBreed()).isEqualTo("Labrador");
        assertThat(pet.getAge()).isEqualTo(5);
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Pet pet = new Pet();

        pet.setId("2");
        pet.setName("Milo");
        pet.setType(PetType.CAT);
        pet.setBreed("Persian");
        pet.setAge(3);

        assertThat(pet.getId()).isEqualTo("2");
        assertThat(pet.getName()).isEqualTo("Milo");
        assertThat(pet.getType()).isEqualTo(PetType.CAT);
        assertThat(pet.getBreed()).isEqualTo("Persian");
        assertThat(pet.getAge()).isEqualTo(3);
    }

    @Test
    void testBuilder() {
        Pet pet = Pet.builder()
                .id("3")
                .name("Buddy")
                .type(PetType.DOG)
                .breed("Beagle")
                .age(4)
                .build();

        assertThat(pet.getId()).isEqualTo("3");
        assertThat(pet.getName()).isEqualTo("Buddy");
        assertThat(pet.getType()).isEqualTo(PetType.DOG);
        assertThat(pet.getBreed()).isEqualTo("Beagle");
        assertThat(pet.getAge()).isEqualTo(4);
    }

    @Test
    void testEqualsAndHashCode() {
        Pet pet1 = new Pet("4", "Luna", PetType.CAT, "Siamese", 2);
        Pet pet2 = new Pet("4", "Luna", PetType.CAT, "Siamese", 2);
        Pet pet3 = new Pet("5", "Rocky", PetType.DOG, "Bulldog", 6);

        assertThat(pet1).isEqualTo(pet2);
        assertThat(pet1.hashCode()).isEqualTo(pet2.hashCode());
        assertThat(pet1).isNotEqualTo(pet3);
    }

    @Test
    void testToString() {
        Pet pet = new Pet("6", "Charlie", PetType.DOG, "Poodle", 1);
        String petString = pet.toString();

        assertThat(petString).contains("Charlie");
        assertThat(petString).contains("DOG");
        assertThat(petString).contains("Poodle");
        assertThat(petString).contains("1");
    }

    @Test
    void testEquals_sameReference() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        assertThat(pet.equals(pet)).isTrue();
    }

    @Test
    void testEquals_nullComparison() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        assertThat(pet.equals(null)).isFalse();
    }

    @Test
    void testEquals_differentClass() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        String other = "NotAPet";
        assertThat(pet.equals(other)).isFalse();
    }

    @Test
    void testEquals_differentValues() {
        Pet pet1 = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        Pet pet2 = new Pet("2", "Bella", PetType.CAT, "Persian", 3);

        assertThat(pet1).isNotEqualTo(pet2);
    }

    @Test
    void testHashCode_consistency() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        int hash1 = pet.hashCode();
        int hash2 = pet.hashCode();

        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void testHashCode_unequalObjects() {
        Pet pet1 = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        Pet pet2 = new Pet("2", "Bella", PetType.CAT, "Persian", 3);

        assertThat(pet1.hashCode()).isNotEqualTo(pet2.hashCode());
    }

    @Test
    void testPetBuilderToString() {
        Pet.PetBuilder builder = Pet.builder()
                .id("1")
                .name("Buddy")
                .type(PetType.DOG)
                .breed("Beagle")
                .age(4);

        String builderStr = builder.toString();

        assertThat(builderStr).contains("id=1");
        assertThat(builderStr).contains("name=Buddy");
        assertThat(builderStr).contains("type=DOG");
        assertThat(builderStr).contains("breed=Beagle");
        assertThat(builderStr).contains("age=4");
    }

    @Test
    void testCanEqual_trueAndFalseCases() {
        Pet pet = new Pet("1", "Max", PetType.DOG, "Labrador", 5);
        Pet otherPet = new Pet("1", "Max", PetType.DOG, "Labrador", 5); // Create equal pet
        String notAPet = "string";

        assertThat(pet.equals(otherPet)).isTrue();
        assertThat(pet.equals(notAPet)).isFalse();
    }
}
