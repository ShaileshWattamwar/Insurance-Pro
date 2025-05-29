package pet_Insurance.repository;


import pet_Insurance.entities.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {

}
