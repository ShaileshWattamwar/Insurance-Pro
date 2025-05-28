package pet_Insurance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pet_Insurance.entities.ClaimData;

import java.util.List;

public interface ClaimsRepository extends MongoRepository<ClaimData, String> {


    List<ClaimData> findByPolicyId(String policyId);

}