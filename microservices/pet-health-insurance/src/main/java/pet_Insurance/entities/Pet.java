package pet_Insurance.entities;


import pet_Insurance.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    @Id
    private String id;
    private String name;
    private PetType type;
    private String breed;
    private int age;
    private String ownerId;
}
