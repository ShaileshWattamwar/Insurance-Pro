package pet_Insurance.service.serviceImp;

import pet_Insurance.dto.PolicyResponse;
import pet_Insurance.entities.Pet;
import pet_Insurance.entities.Policy;
import pet_Insurance.enums.PetType;
import pet_Insurance.repository.PetRepository;
import pet_Insurance.repository.PolicyRepository;
import pet_Insurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PetRepository petRepository;
    private final PolicyRepository policyRepository;

    @Override
    public PolicyResponse applyPolicy(String petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        double premium = calculatePremium(pet.getType(), pet.getBreed(), pet.getAge());
        Policy policy = Policy.builder()
                .petId(petId)
                .premiumAmountInINR(premium)
                .isClaimed(false)
                .build();

        Policy saved = policyRepository.save(policy);
        return new PolicyResponse(saved.getId(), saved.getPremiumAmountInINR(), saved.isClaimed());
    }

    @Override
    public PolicyResponse claimPolicy(String policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setClaimed(true);
        Policy updated = policyRepository.save(policy);
        return new PolicyResponse(updated.getId(), updated.getPremiumAmountInINR(), updated.isClaimed());
    }

    private double calculatePremium(PetType type, String breed, int age) {
        double base = switch (type) {
            case DOG -> 100;
            case CAT -> 70;
            case HORSE -> 150;
        };

        double risk = switch (breed.toLowerCase()) {
            case "german shepherd" -> 1.5;
            case "persian" -> 1.3;
            case "arabian" -> 1.1;
            default -> 1.0;
        };

        double ageMultiplier = age <= 2 ? 1.0 : age <= 6 ? 1.2 : 1.5;
        return base * risk * ageMultiplier * 83;
    }
}
