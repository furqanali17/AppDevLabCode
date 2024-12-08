package Lab_5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String animalType;
    private String breed;
    private int age;

    @ManyToOne
    @JoinColumn(name = "household_eircode", nullable = false)
    private Household household;

    public String getHouseholdEircode() {
        return household != null ? household.getEircode() : null;
    }
}
