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

    @Override
    public Pet addPet(PetRequest request) {
        Pet pet = Pet.builder()
                .name(request.getName())
                .type(request.getType())
                .breed(request.getBreed())
                .age(request.getAge())
                .build();
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(String id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    @Override
    public Pet updatePet(String id, PetRequest request) {
        Pet pet = getPetById(id);
        pet.setName(request.getName());
        pet.setType(request.getType());
        pet.setBreed(request.getBreed());
        pet.setAge(request.getAge());
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(String id) {
        petRepository.deleteById(id);
    }
}
