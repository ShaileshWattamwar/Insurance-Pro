package pet_Insurance.entities;

import pet_Insurance.enums.PetType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "pets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    private String id;
    @Field("pet_name")
    private String name;
    @Field("pet_type")
    private PetType type;
    @Field("breed")
    private String breed;
    @Field("pet_age")
    private int age;
}
