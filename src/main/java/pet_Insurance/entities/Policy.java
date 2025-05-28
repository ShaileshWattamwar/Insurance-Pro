package pet_Insurance.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "policies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {
    @Id
    private String id;
    private String petId;
    private double premiumAmountInINR;
    private boolean isClaimed;
}
