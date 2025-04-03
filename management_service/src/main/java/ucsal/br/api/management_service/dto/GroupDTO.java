package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class GroupDTO {

    private UUID id;
    private String name;
    private Boolean availableForProjects;
    private UserDTO coordinator;
    private List<UserDTO> students;

    public GroupDTO() {}

    public GroupDTO(String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupDTO(UUID id, String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    // Construtor usando GroupEntity e entidades resgatadas do banco
    public GroupDTO(GroupEntity entity, UserEntity coordinator, List<UserEntity> students) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.availableForProjects = entity.isAvailableForProjects(); // CORRIGIDO
        this.coordinator = new UserDTO(coordinator);
        this.students = students.stream().map(UserDTO::new).toList();
    }

    // Getters e Setters

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
