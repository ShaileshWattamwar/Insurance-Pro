package pet_Insurance.controllers;

import pet_Insurance.dto.PetRequest;
import pet_Insurance.entities.Pet;
import pet_Insurance.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/add")
    public Pet addPet(@RequestBody PetRequest request) {
        return petService.addPet(request);
    }

    @GetMapping("/get")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/get/{id}")
    public Pet getPetById(@PathVariable String id) {
        return petService.getPetById(id);
    }

    @PutMapping("/update/{id}")
    public Pet updatePet(@PathVariable String id, @RequestBody PetRequest request) {
        return petService.updatePet(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable String id) {
        petService.deletePet(id);
    }
}
