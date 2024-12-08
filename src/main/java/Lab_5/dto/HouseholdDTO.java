package Lab_5.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HouseholdDTO(
        @NotBlank(message = "Eircode is mandatory")
        String eircode,

        @NotNull(message = "Number of occupants is mandatory")
        @Min(value = 0, message = "Number of occupants must be at least 0")
        Integer numberOfOccupants,

        @NotNull(message = "Maximum number of occupants is mandatory")
        @Max(value = 10, message = "Maximum occupants cannot exceed 10")
        Integer maxNumberOfOccupants,

        @NotNull(message = "Owner occupied is mandatory")
        Boolean ownerOccupied
) {}
