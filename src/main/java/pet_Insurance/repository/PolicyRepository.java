package pet_Insurance.repository;


import pet_Insurance.entities.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PolicyRepository extends MongoRepository<Policy, String> {

}
