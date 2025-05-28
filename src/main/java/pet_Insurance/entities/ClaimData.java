package pet_Insurance.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "claims")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimData {
    @Id
    private String claimId;
    private String policyId;
    private String description;
    private double claimAmount;
    private String claimStatus;
    private LocalDate claimDate;
}