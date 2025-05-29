package pet_Insurance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pet_Insurance.entities.OwnerDeteail;

public interface OwnerRepository extends MongoRepository<OwnerDeteail, String> {


}