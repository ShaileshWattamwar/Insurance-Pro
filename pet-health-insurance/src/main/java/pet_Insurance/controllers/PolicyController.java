package pet_Insurance.controllers;

import pet_Insurance.dto.PolicyResponse;
import pet_Insurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
