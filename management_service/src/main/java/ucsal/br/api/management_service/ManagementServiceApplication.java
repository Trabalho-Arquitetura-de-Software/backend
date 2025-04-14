package ucsal.br.api.management_service;

import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.service.GroupService;
import ucsal.br.api.management_service.service.ProjectService;
import ucsal.br.api.management_service.service.UserService;
import ucsal.br.api.management_service.utils.exception.GroupAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.GroupNotFoundException;
import ucsal.br.api.management_service.utils.exception.ProjectAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.UserAlredyExistsException;
import ucsal.br.api.management_service.utils.type.ProjectStatus;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementServiceApplication.class, args);
    }

    @Bean
    @Order(1)
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
                System.out.println("✅ Usuário admin criado com sucesso!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Usuário admin já existe, ignorando criação.");
            } catch (Exception e) {
                System.out.println("❌ Erro ao criar usuário admin: " + e.getMessage());
            }
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner createProfessorUser(UserService userService) {
        return args -> {
            String professorEmail = "professor@test.com";
            String professorPassword = "professor123";


            try {
                UserDTO professorUser = new UserDTO();
                professorUser.setName("professor");
                professorUser.setEmail(professorEmail);
                professorUser.setPassword(professorPassword);
                professorUser.setRole(UserRole.PROFESSOR);

                userService.saveUser(professorUser);
                System.out.println("✅ Professor user created successfully!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Professor user already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating professor user: " + e.getMessage());
            }
        };
    }

    @Bean
    @Order(3)
    CommandLineRunner createStudentsUser(UserService userService) {
        return args -> {
            String student01Email = "student01@test.com";
            String student01Password = "student123";

            String student02Email = "student02@test.com";
            String student02Password = "student123";

            String student03Email = "student03@test.com";
            String student03Password = "student123";

            try {
                UserDTO studentUser = new UserDTO();
                studentUser.setName("student01");
                studentUser.setEmail(student01Email);
                studentUser.setPassword(student01Password);
                studentUser.setRole(UserRole.STUDENT);

                userService.saveUser(studentUser);
                System.out.println("✅ Student01 user created successfully!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Student01 user already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating student01 user: " + e.getMessage());
            }

            try {
                UserDTO studentUser = new UserDTO();
                studentUser.setName("student02");
                studentUser.setEmail(student02Email);
                studentUser.setPassword(student02Password);
                studentUser.setRole(UserRole.STUDENT);

                userService.saveUser(studentUser);
                System.out.println("✅ Student02 user created successfully!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Student02 user already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating student02 user: " + e.getMessage());
            }

            try {
                UserDTO studentUser = new UserDTO();
                studentUser.setName("student03");
                studentUser.setEmail(student03Email);
                studentUser.setPassword(student03Password);
                studentUser.setRole(UserRole.STUDENT);

                userService.saveUser(studentUser);
                System.out.println("✅ Student03 user created successfully!");
            } catch (UserAlredyExistsException e) {
                System.out.println("⚠️ Student03 user already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating student01 user: " + e.getMessage());
            }
        };
    }

    @Bean
    @Order(4)
    CommandLineRunner createGroup(GroupService groupService, UserService userService) {
        return args -> {
            String groupName = "group1";

            String coordinatorEmail = "professor@test.com";

            String student01Email = "student01@test.com";
            String student02Email = "student02@test.com";
            String student03Email = "student03@test.com";

            UserDTO coordinator = userService.findUserByEmail(coordinatorEmail);

            List<UserDTO> students = new ArrayList<>();
            students.add(userService.findUserByEmail(student01Email));
            students.add(userService.findUserByEmail(student02Email));
            students.add(userService.findUserByEmail(student03Email));

            try {
                GroupDTO group = new GroupDTO();
                group.setName(groupName);
                group.setAvailableForProjects(true);
                group.setCoordinator(coordinator);
                group.setStudents(students);

                groupService.saveGroup(group);
                System.out.println("✅ Group created successfully!");
            } catch (GroupAlredyExistsException e) {
                System.out.println("⚠️ Group Already exists, ignoring creation.");
            } catch (Exception e) {
                System.out.println("❌ Error creating group: " + e.getMessage());
            }
        };
    }
}
