package ucsal.br.api.management_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.service.UserService;
import ucsal.br.api.management_service.utils.exception.UserAlredyExistsException;
import ucsal.br.api.management_service.utils.type.UserRole;

@SpringBootApplication
public class ManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner createAdminUser(UserService userService) {
        return args -> {
            String adminEmail = "admin@test.com";
            String adminPassword = "admin123";

            try {
                UserDTO adminUser = new UserDTO();
                adminUser.setName("admin");
                adminUser.setEmail(adminEmail);
                adminUser.setPassword(adminPassword);
                adminUser.setRole(UserRole.ADMIN);

                userService.saveUser(adminUser);
                System.out.println("✅ Admin user created successfully!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Admin user already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating admin user: " + e.getMessage());
            }
        };
    }
}
