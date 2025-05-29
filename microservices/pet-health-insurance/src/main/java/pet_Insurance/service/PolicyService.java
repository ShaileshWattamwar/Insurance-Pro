package pet_Insurance.service;

import pet_Insurance.dto.PolicyResponse;

public interface PolicyService {
    PolicyResponse applyPolicy(String petId);
    PolicyResponse claimPolicy(String policyId);
}
