package Lab_5.service;

import Lab_5.entity.Pet;
import Lab_5.dto.PetDTO;
import Lab_5.repository.PetRepository;
import Lab_5.dto.PetStatistics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pet not found with ID: " + id));
    }

    @Override
    public Pet updatePet(Long id, Pet updatedPet) {
        Pet existingPet = getPetById(id);
        existingPet.setName(updatedPet.getName());
        existingPet.setAge(updatedPet.getAge());
        existingPet.setBreed(updatedPet.getBreed());
        existingPet.setAnimalType(updatedPet.getAnimalType());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long id) {
        getPetById(id); // Ensure the pet exists before attempting to delete
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> petsToDelete = petRepository.findByNameIgnoreCase(name);
        if (petsToDelete.isEmpty()) {
            throw new IllegalArgumentException("No pets found with name: " + name);
        }
        petRepository.deleteAll(petsToDelete);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<PetDTO> getNameAndBreedOnly() {
        return petRepository.findAll().stream()
                .map(pet -> new PetDTO(
                        pet.getName(),
                        pet.getAnimalType(),
                        pet.getBreed(),
                        pet.getAge(),
                        pet.getHousehold() != null ? pet.getHousehold().getEircode() : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PetStatistics getPetStatistics() {
        List<Pet> pets = petRepository.findAll();
        double averageAge = pets.stream().mapToInt(Pet::getAge).average().orElse(0.0);
        int oldestAge = pets.stream().mapToInt(Pet::getAge).max().orElse(0);
        return new PetStatistics(pets.size(), averageAge, oldestAge);
    }

}
