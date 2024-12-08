package Lab_5;

import Lab_5.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Lab5ApplicationTests {
	@Autowired
	PetRepository petRepository;

	@Test
	void contextLoads() {
		assertNotNull(petRepository, "PetRepository should not be null");
	}

}
