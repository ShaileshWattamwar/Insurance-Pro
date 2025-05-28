package pet_Insurance.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyResponse {
    private String policyId;
    private double premiumAmountInINR;
    private boolean isClaimed;
}
