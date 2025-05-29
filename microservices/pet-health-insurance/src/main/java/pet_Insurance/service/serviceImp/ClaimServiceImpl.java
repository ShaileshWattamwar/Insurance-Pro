package pet_Insurance.service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet_Insurance.entities.ClaimData;
import pet_Insurance.repository.ClaimsRepository;
import pet_Insurance.service.ClaimService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimsRepository claimRepository;

    @Override
    public List<ClaimData> getClaimsByPolicyId(String policyId) {
        return claimRepository.findByPolicyId(policyId);
    }

    @Override
    public List<ClaimData> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public ClaimData submitClaim(ClaimData claim) {
        claim.setClaimStatus("PENDING");
        claim.setClaimDate(LocalDate.now());
        return claimRepository.save(claim);
    }

    @Override
    public ClaimData approveClaim(String claimId) {
        return updateStatus(claimId, "APPROVED");
    }

    @Override
    public ClaimData rejectClaim(String claimId) {
        return updateStatus(claimId, "REJECTED");
    }

    private ClaimData updateStatus(String claimId, String status) {
        Optional<ClaimData> existing = claimRepository.findById(claimId);
        if (existing.isPresent()) {
            ClaimData claim = existing.get();
            claim.setClaimStatus(status);
            return claimRepository.save(claim);
        }
        return null;
    }

}
