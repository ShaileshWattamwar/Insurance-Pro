package pet_Insurance.service.serviceImp;

import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;
import pet_Insurance.repository.PetRepository;
import pet_Insurance.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private static final String PET_NOT_FOUND_MSG = "Pet not found with the provided id: ";

     // this method adds a new pet
    @Override
    public Pet addPet(PetRequest request) {
        return petRepository.save(toPet(request));
    }

    // it returns all the pets which had been enrolled
    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // finds the pets by id and returns value
    @Override
    public Pet getPetById(String id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(PET_NOT_FOUND_MSG + id));
    }

   // Updates an existing pet
    @Override
    public Pet updatePet(String id, PetRequest request) {
        Pet pet = getPetById(id);
        updatePetFromRequest(pet, request);
        return petRepository.save(pet);
    }

    // this method deletes the records by id.
    @Override
    public void deletePet(String id) {
        petRepository.deleteById(id);
    }

    // Helper to convert PetRequest DTO to Pet entity
    private Pet toPet(PetRequest request) {
        return Pet.builder()
                .name(request.getName())
                .type(request.getType())
                .breed(request.getBreed())
                .age(request.getAge())
                .build();
    }

    // Helper to update an existing Pet entity with PetRequest data
    private void updatePetFromRequest(Pet pet, PetRequest request) {
        pet.setName(request.getName());
        pet.setType(request.getType());
        pet.setBreed(request.getBreed());
        pet.setAge(request.getAge());
    }
}
