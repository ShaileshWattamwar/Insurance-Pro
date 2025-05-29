package pet_Insurance.controllers;

import org.springframework.web.bind.annotation.*;
import pet_Insurance.dto.PolicyResponse;
import pet_Insurance.service.PolicyService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping("/apply/{petId}")
    public PolicyResponse applyPolicy(@PathVariable String petId) {
        return policyService.applyPolicy(petId);
    }

    @PostMapping("/claim/{policyId}")
    public PolicyResponse claimPolicy(@PathVariable String policyId) {
        return policyService.claimPolicy(policyId);
    }
}
