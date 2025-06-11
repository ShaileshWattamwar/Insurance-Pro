package pet_Insurance.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pet_Insurance.enums.PetType;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetRequest {
    private String name;
    private PetType type;
    private String breed;
    private int age;
}
