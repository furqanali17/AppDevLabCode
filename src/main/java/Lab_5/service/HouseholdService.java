package Lab_5.service;

import Lab_5.entity.Household;

import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);

    List<Household> getAllHouseholds();

    Household getHouseholdById(String eircode);

    Household getHouseholdByIdWithPets(String eircode);

    Household updateHousehold(String eircode, Household updatedHousehold);

    void deleteHousehold(String eircode);

    List<Household> findHouseholdsWithNoPets();

    List<Household> findOwnerOccupiedHouseholds();

    int countEmptyHouses();

    int countFullHouses();
}
