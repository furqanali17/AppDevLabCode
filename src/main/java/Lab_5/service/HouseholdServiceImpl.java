package Lab_5.service;

import Lab_5.entity.Household;
import Lab_5.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdById(String eircode) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new IllegalArgumentException("Household not found with eircode: " + eircode));
    }

    @Override
    public Household getHouseholdByIdWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household updatedHousehold) {
        Household existingHousehold = getHouseholdById(eircode);
        existingHousehold.setNumberOfOccupants(updatedHousehold.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(updatedHousehold.getMaxNumberOfOccupants());
        existingHousehold.setOwnerOccupied(updatedHousehold.isOwnerOccupied());
        return householdRepository.save(existingHousehold);
    }

    @Override
    public void deleteHousehold(String eircode) {
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public int countEmptyHouses() {
        return (int) householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == 0)
                .count();
    }

    @Override
    public int countFullHouses() {
        return (int) householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == h.getMaxNumberOfOccupants())
                .count();
    }
}
