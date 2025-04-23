package ucsal.br.api.management_service.service;

import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.ProjectEntity;
import ucsal.br.api.management_service.repository.IGroupRepository;
import ucsal.br.api.management_service.repository.IProjectRepository;
import ucsal.br.api.management_service.repository.IUserRepository;
import ucsal.br.api.management_service.utils.exception.*;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;
    private final IUserRepository userRepository;
    private final IGroupRepository groupRepository;

    public ProjectService(IProjectRepository projectRepository, IUserRepository userRepository, IGroupRepository groupRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    private ProjectDTO createProjectDTO(ProjectEntity projectEntity) {
        return getProjectDTO(projectEntity, userRepository, groupRepository);
    }

    static ProjectDTO getProjectDTO(ProjectEntity projectEntity, IUserRepository userRepository, IGroupRepository groupRepository) {
        UserDTO userDTO = new UserDTO(userRepository.findById(projectEntity.getRequester()).orElseThrow(() -> new UserNotFoundException("User Not Found")));

        GroupDTO groupDTO = null;

        if (projectEntity.getGroup() != null) {
            GroupEntity group = groupRepository.findById(projectEntity.getGroup()).orElseThrow(() -> new GroupNotFoundException("Group Not Found"));
            groupDTO = new GroupDTO(group.getId(), group.getName(), group.isAvailableForProjects(), new UserDTO(userRepository.findById(group.getCoordinator()).orElseThrow(() -> new UserNotFoundException("Coordinator Not Found"))), userRepository.findAllById(group.getStudents()).stream().map(UserDTO::new).collect(Collectors.toList()));
        }

        return new ProjectDTO(projectEntity.getId(), projectEntity.getName(), projectEntity.getObjective(), projectEntity.getSummaryScope(), projectEntity.getTargetAudience(), projectEntity.getExpectedStartDate(), projectEntity.getStatus(), userDTO, groupDTO);
    }

    public List<ProjectDTO> findAllProjects() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        return projectEntities.stream().map(this::createProjectDTO).collect(Collectors.toList());
    }

    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        var userOpt = userRepository.findById(projectDTO.getRequester().getId());

        if (userOpt.isEmpty() || userOpt.get().getRole() != UserRole.PROFESSOR) {
            throw new UserNotFoundException("Professor Not Found");
        }

        ProjectEntity projectEntity = new ProjectEntity(projectDTO);

        // Remove o grupo ao salvar
        projectEntity.setGroup(null);

        projectEntity = projectRepository.save(projectEntity);
        return createProjectDTO(projectEntity);
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectRepository.findById(projectDTO.getId())
                .orElseThrow(() -> new ProjectNotFoundException("Project Not Found"));

        if (projectDTO.getId() != null) {
            projectEntity.setId(projectDTO.getId());
        }
        if (projectDTO.getName() != null) {
            projectEntity.setName(projectDTO.getName());
        }
        if (projectDTO.getObjective() != null) {
            projectEntity.setObjective(projectDTO.getObjective());
        }
        if (projectDTO.getSummaryScope() != null) {
            projectEntity.setSummaryScope(projectDTO.getSummaryScope());
        }
        if (projectDTO.getTargetAudience() != null) {
            projectEntity.setTargetAudience(projectDTO.getTargetAudience());
        }
        if (projectDTO.getExpectedStartDate() != null) {
            projectEntity.setExpectedStartDate(projectDTO.getExpectedStartDate());
        }
        if (projectDTO.getStatus() != null) {
            projectEntity.setStatus(projectDTO.getStatus());
        }
        if (projectDTO.getRequester() != null && projectDTO.getRequester().getId() != null) {
            var userOpt = userRepository.findById(projectDTO.getRequester().getId());
            userOpt.ifPresent(user -> {
                if (user.getRole() == UserRole.PROFESSOR) {
                    projectEntity.setRequester(user.getId());
                }
            });
        }
        if (projectDTO.getGroup() != null && projectDTO.getGroup().getId() != null) {
            if (projectDTO.getGroup().getAvailableForProjects()){
                var groupOpt = groupRepository.findById(projectDTO.getGroup().getId());
                groupOpt.ifPresent(group -> projectEntity.setGroup(group.getId()));
            }
        }

        return createProjectDTO(projectRepository.save(projectEntity));
    }
}