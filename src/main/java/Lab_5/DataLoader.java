package Lab_5;

import Lab_5.entity.User;
import Lab_5.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("user@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("USER");
            user.setLocked(false);
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setCounty("Cork");
            userRepository.save(user);

            User admin = new User();
            admin.setUsername("admin@example.com");
            admin.setPassword(passwordEncoder.encode("adminpassword"));
            admin.setRole("ADMIN");
            admin.setLocked(false);
            admin.setFirstName("Jane");
            admin.setLastName("Doe");
            admin.setCounty("Kerry");
            userRepository.save(admin);
        };
    }
}
