package Lab_5.controller;

import Lab_5.dto.HouseholdDTO;
import Lab_5.dto.HouseholdStatistics;
import Lab_5.dto.PetDTO;
import Lab_5.entity.Household;
import Lab_5.entity.Pet;
import Lab_5.service.HouseholdService;
import Lab_5.service.PetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    private final HouseholdService householdService;
    private final PetService petService;

    public GraphQLController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    // Queries
    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household getHousehold(@Argument String eircode) {
        return householdService.getHouseholdById(eircode);
    }

    @QueryMapping
    public Pet getPet(@Argument Long id) {
        return petService.getPetById(id);
    }

    @QueryMapping
    public HouseholdStatistics getHouseholdStatistics() {
        int totalHouseholds = householdService.getAllHouseholds().size();
        int emptyHouseholds = householdService.countEmptyHouses();
        int fullHouseholds = householdService.countFullHouses();
        return new HouseholdStatistics(totalHouseholds, emptyHouseholds, fullHouseholds);
    }

    @Secured("ROLE_USER")
    @QueryMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Mutations
    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Pet createPet(@Argument PetDTO input) {
        Pet pet = new Pet();
        pet.setName(input.name());
        pet.setAnimalType(input.animalType());
        pet.setBreed(input.breed());
        pet.setAge(input.age());

        Household household = householdService.getHouseholdById(input.householdEircode());
        if (household == null) {
            throw new IllegalArgumentException("Household with eircode " + input.householdEircode() + " not found.");
        }

        pet.setHousehold(household);
        return petService.createPet(pet);
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Household createHousehold(@Argument HouseholdDTO input) {
        Household household = new Household();
        household.setEircode(input.eircode());
        household.setNumberOfOccupants(input.numberOfOccupants());
        household.setMaxNumberOfOccupants(input.maxNumberOfOccupants());
        household.setOwnerOccupied(input.ownerOccupied());
        return householdService.createHousehold(household);
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Boolean deleteHouseholdById(@Argument String eircode) {
        householdService.deleteHousehold(eircode);
        return true;
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Boolean deletePetById(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}
