package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GroupDTO {

    private UUID id;
    private String name;
    private Boolean availableForProjects;
    private UserDTO coordinator;
    private List<UserDTO> students;

    public GroupDTO() {}

    public GroupDTO(String name, Boolean availableForProjects, UUID coordinator, List<UUID> students) {
        this.name = name;
        this.availableForProjects = availableForProjects;

        this.coordinator = new UserDTO();
        this.coordinator.setId(coordinator);

        this.students = students.stream().map(uuid -> {
            UserDTO student = new UserDTO();
            student.setId(uuid);
            return student;
        }).collect(Collectors.toList());
    }

    public GroupDTO(UUID id, String name, Boolean availableForProjects, UUID coordinator, List<UUID> students) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;

        this.coordinator = new UserDTO();
        this.coordinator.setId(coordinator);

        this.students = students.stream().map(uuid -> {
            UserDTO student = new UserDTO();
            student.setId(uuid);
            return student;
        }).collect(Collectors.toList());
    }

    public GroupDTO(UUID id, String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupDTO(GroupEntity groupEntity) {
        this.id = groupEntity.getId();
        this.name = groupEntity.getName();
        this.availableForProjects = groupEntity.isAvailableForProjects();

        this.coordinator = new UserDTO();
        this.coordinator.setId(groupEntity.getCoordinator());

        this.students = groupEntity.getStudents().stream()
                .map(uuid -> {
                    UserDTO student = new UserDTO();
                    student.setId(uuid);
                    return student;
                })
                .collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailableForProjects() {
        return availableForProjects;
    }

    public void setAvailableForProjects(Boolean availableForProjects) {
        this.availableForProjects = availableForProjects;
    }

    public UserDTO getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UserDTO coordinator) {
        this.coordinator = coordinator;
    }

    public List<UserDTO> getStudents() {
        return students;
    }

    public void setStudents(List<UserDTO> students) {
        this.students = students;
    }
}
