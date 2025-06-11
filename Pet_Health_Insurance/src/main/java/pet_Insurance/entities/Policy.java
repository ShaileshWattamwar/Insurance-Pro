package pet_Insurance.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "policies")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Policy {

    @Id
    private String id;
    @Field("pet_id")
    private String petId;
    @Field("premium_Amount")
    private double premiumAmountInINR;
    @Field("is_claimed")
    private boolean isClaimed;
}
