package Lab_5.controller;

import Lab_5.entity.Household;
import Lab_5.service.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@RequestBody Household household) {
        return ResponseEntity.ok(householdService.createHousehold(household));
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdById(eircode));
    }

    @GetMapping("/{eircode}/pets")
    public ResponseEntity<Household> getHouseholdByIdWithPets(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdByIdWithPets(eircode));
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<Household> updateHousehold(@PathVariable String eircode, @RequestBody Household updatedHousehold) {
        return ResponseEntity.ok(householdService.updateHousehold(eircode, updatedHousehold));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHousehold(eircode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> findHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<Household>> findOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.findOwnerOccupiedHouseholds());
    }

    @GetMapping("/stats/empty-houses")
    public ResponseEntity<Integer> countEmptyHouses() {
        return ResponseEntity.ok(householdService.countEmptyHouses());
    }

    @GetMapping("/stats/full-houses")
    public ResponseEntity<Integer> countFullHouses() {
        return ResponseEntity.ok(householdService.countFullHouses());
    }
}
