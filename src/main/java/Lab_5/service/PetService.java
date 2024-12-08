package Lab_5.service;

import Lab_5.dto.PetDTO;
import Lab_5.dto.PetStatistics;
import Lab_5.entity.Pet;
import java.util.List;

public interface PetService {
    List<Pet> getAllPets();
    Pet createPet(Pet pet);
    Pet getPetById(Long id);


    Pet updatePet(Long id, Pet updatedPet);

    void deletePetById(Long id);

    void deletePetsByName(String name);

    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);

    List<PetDTO> getNameAndBreedOnly();

    PetStatistics getPetStatistics();

}
