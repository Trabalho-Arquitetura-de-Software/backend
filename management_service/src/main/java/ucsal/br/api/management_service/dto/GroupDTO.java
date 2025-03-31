package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class GroupDTO {

    private UUID id;
    private String name;
    private Boolean availableForProjects;
    private UserDTO coordinator;
    private List<UserDTO> students;

    public GroupDTO() {}

    public GroupDTO(String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.name = Objects.requireNonNull(name, "O nome não pode ser nulo");
        this.availableForProjects = Objects.requireNonNull(availableForProjects, "Disponibilidade não pode ser nula");
        this.coordinator = Objects.requireNonNull(coordinator, "Coordenador não pode ser nulo");
        this.students = Objects.requireNonNull(students, "Lista de estudantes não pode ser nula");
    }

    public GroupDTO(UUID id, String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.name = Objects.requireNonNull(name, "O nome não pode ser nulo");
        this.availableForProjects = Objects.requireNonNull(availableForProjects, "Disponibilidade não pode ser nula");
        this.coordinator = Objects.requireNonNull(coordinator, "Coordenador não pode ser nulo");
        this.students = Objects.requireNonNull(students, "Lista de estudantes não pode ser nula");
    }

    // ✅ Construtor baseado em GroupEntity (substitui convertToDTO)
    public GroupDTO(GroupEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.availableForProjects = entity.getAvailableForProjects();
        this.coordinator = new UserDTO(entity.getCoordinator());
        this.students = entity.getStudents().stream().map(UserDTO::new).collect(Collectors.toList());
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
