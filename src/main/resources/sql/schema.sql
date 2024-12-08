CREATE TABLE pets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    animal_type VARCHAR(255) NOT NULL,
    breed VARCHAR(255) NOT NULL,
    age INT NOT NULL
    household_eircode VARCHAR(8),
    CONSTRAINT fk_household FOREIGN KEY (household_eircode) REFERENCES household(eircode) ON DELETE CASCADE
);

CREATE TABLE household (
    eircode VARCHAR(8) PRIMARY KEY,
    number_of_occupants INT NOT NULL,
    max_number_of_occupants INT NOT NULL,
    owner_occupied BIT NOT NULL
);

