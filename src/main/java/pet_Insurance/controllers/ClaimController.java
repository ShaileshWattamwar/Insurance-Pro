package pet_Insurance.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_Insurance.entities.ClaimData;
import pet_Insurance.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ClaimData submitClaim(@RequestBody ClaimData claim) {

        return claimService.submitClaim(claim);
    }

    @PostMapping("/{id}/approve")
    public ClaimData approveClaim(@PathVariable String id) {

        return claimService.approveClaim(id);
    }

    @PostMapping("/{id}/reject")
    public ClaimData rejectClaim(@PathVariable String id) {

        return claimService.rejectClaim(id);
    }

    @GetMapping("/policy/{policyId}")
    public List<ClaimData> getClaimsByPolicy(@PathVariable String policyId) {
        return claimService.getClaimsByPolicyId(policyId);
    }
}