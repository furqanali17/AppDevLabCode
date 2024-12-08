package Lab_5.controller;

import Lab_5.dto.PetDTO;
import Lab_5.entity.Pet;
import Lab_5.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.name());
        pet.setAnimalType(petDTO.animalType());
        pet.setBreed(petDTO.breed());
        pet.setAge(petDTO.age());
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Pet> changePetName(@PathVariable Long id, @RequestParam String name) {
        Pet pet = petService.getPetById(id);
        pet.setName(name);
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }
}
