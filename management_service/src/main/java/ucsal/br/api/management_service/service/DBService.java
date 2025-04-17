package ucsal.br.api.management_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.utils.exception.GroupAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.ProjectAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.UserAlredyExistsException;
import ucsal.br.api.management_service.utils.type.ProjectStatus;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    private final UserService userService;
    private final GroupService groupService;
    private final ProjectService projectService;

    public DBService(UserService userService, GroupService groupService, ProjectService projectService) {
        this.userService = userService;
        this.groupService = groupService;
        this.projectService = projectService;
    }

    public boolean populateTheDatabase() {
        try {
            createUserProfessor();
            for (int i = 1; i <= 3; i++) {
                createUserStudent(i);
            }
            createGroup();
            createProject();

            return true;

        } catch (UserAlredyExistsException | GroupAlredyExistsException | ProjectAlredyExistsException e) {
            System.out.println("⚠️ Entities already exist, ignoring the population");
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error in popular Database: " + e.getMessage());
            return false;
        }
    }

    private void createUserProfessor() throws UserAlredyExistsException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Professor");
        userDTO.setEmail("professor@test.com");
        String encryptedPassword = new BCryptPasswordEncoder().encode("professor123");
        userDTO.setPassword(encryptedPassword);
        userDTO.setRole(UserRole.PROFESSOR);

        userService.saveUser(userDTO);
    }

    private void createUserStudent(int studentNumber) throws UserAlredyExistsException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Student" + studentNumber);
        userDTO.setEmail("student" + studentNumber + "@test.com");
        String encryptedPassword = new BCryptPasswordEncoder().encode("student123");
        userDTO.setPassword(encryptedPassword);
        userDTO.setRole(UserRole.STUDENT);

        userService.saveUser(userDTO);
    }

    private void createGroup() throws GroupAlredyExistsException {
        GroupDTO groupDTO = new GroupDTO();

        String coordinatorEmail = "professor@test.com";

        String student01Email = "student1@test.com";
        String student02Email = "student2@test.com";
        String student03Email = "student3@test.com";

        UserDTO coordinator = userService.findUserByEmail(coordinatorEmail);

        List<UserDTO> students = new ArrayList<>();
        students.add(userService.findUserByEmail(student01Email));
        students.add(userService.findUserByEmail(student02Email));
        students.add(userService.findUserByEmail(student03Email));

        groupDTO.setName("group1");
        groupDTO.setAvailableForProjects(true);
        groupDTO.setCoordinator(coordinator);
        groupDTO.setStudents(students);

        groupService.saveGroup(groupDTO);
    }

    private void createProject() throws ProjectAlredyExistsException {
        UserDTO requester = userService.findUserByEmail("professor@test.com");
        GroupDTO group = groupService.findGroupByName("group1");

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setName("Projeto Integrador I");
        projectDTO.setObjective("Aplicar conhecimentos de desenvolvimento de software");
        projectDTO.setSummaryScope("Desenvolvimento de um sistema de gestão para entidades sociais");
        projectDTO.setTargetAudience("Organizações sociais da comunidade");
        projectDTO.setExpectedStartDate(LocalDate.of(2025, 8, 1));
        projectDTO.setStatus(ProjectStatus.PENDING_ANALYSIS);
        projectDTO.setRequester(requester);
        projectDTO.setGroup(group);

        projectService.saveProject(projectDTO);
    }
}
