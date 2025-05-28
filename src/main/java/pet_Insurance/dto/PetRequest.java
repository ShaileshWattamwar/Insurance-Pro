package pet_Insurance.dto;

import lombok.Builder;
import lombok.Data;
import pet_Insurance.enums.PetType;

@Data
@Builder
public class PetRequest {
    private String name;
    private String breed;
    private PetType type;
    private int age;
    private String ownerId;
}
