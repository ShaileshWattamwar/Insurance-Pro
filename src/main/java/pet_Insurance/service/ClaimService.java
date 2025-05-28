package pet_Insurance.service;

import pet_Insurance.entities.ClaimData;
import java.util.List;

public interface ClaimService {

    ClaimData submitClaim(ClaimData claim);

    ClaimData approveClaim(String claimId);

    ClaimData rejectClaim(String claimId);

    List<ClaimData> getClaimsByPolicyId(String policyId);

    List<ClaimData> getAllClaims();
}