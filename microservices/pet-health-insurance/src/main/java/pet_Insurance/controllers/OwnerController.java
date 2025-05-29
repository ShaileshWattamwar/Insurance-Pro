package pet_Insurance.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet_Insurance.entities.OwnerDeteail;
import pet_Insurance.service.OwnerDetailService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerDetailService ownerService;

    @PostMapping
    public OwnerDeteail createOwner(@RequestBody OwnerDeteail owner) {
        return ownerService.createOwner(owner);
    }

    @GetMapping("/{id}")
    public OwnerDeteail getOwnerById(@PathVariable String id) {
        return ownerService.getOwnerById(id);
    }

    @GetMapping
    public List<OwnerDeteail> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PutMapping("/{id}")
    public OwnerDeteail updateOwner(@PathVariable String id, @RequestBody OwnerDeteail owner) {
        return ownerService.updateOwner(id, owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable String id) {
        ownerService.deleteOwner(id);
    }
}
