package Lab_5.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PetStatistics {

    private int totalCount;
    private double averageAge;
    private int oldestAge;

    public PetStatistics(int totalCount, double averageAge, int oldestAge) {
        this.totalCount = totalCount;
        this.averageAge = averageAge;
        this.oldestAge = oldestAge;
    }

}
