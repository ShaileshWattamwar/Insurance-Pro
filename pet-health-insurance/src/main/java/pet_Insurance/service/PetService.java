package pet_Insurance.service;


import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;

import java.util.List;

public interface PetService {
    Pet addPet(PetRequest request);
    List<Pet> getAllPets();
    Pet getPetById(String id);
    Pet updatePet(String id, PetRequest request);
    void deletePet(String id);
}
