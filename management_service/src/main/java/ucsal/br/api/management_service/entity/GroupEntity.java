package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.UserDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean availableForProjects;

    @Column(name = "coordinator_id", nullable = false)
    private UUID coordinator;

    @ElementCollection
    @CollectionTable(name = "group_students", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "student_id")
    private List<UUID> students;

    public GroupEntity() {}

    public GroupEntity(String name, boolean availableForProjects, UUID coordinator, List<UUID> students) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupEntity(GroupDTO groupDTO) {
        this.id = groupDTO.getId();
        this.name = groupDTO.getName();
        this.availableForProjects = groupDTO.getAvailableForProjects();
        this.coordinator = groupDTO.getCoordinator().getId();
        this.students = groupDTO.getStudents().stream().map(UserDTO::getId).collect(Collectors.toList());
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

    public boolean isAvailableForProjects() {
        return availableForProjects;
    }

    public void setAvailableForProjects(boolean availableForProjects) {
        this.availableForProjects = availableForProjects;
    }

    public UUID getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UUID coordinator) {
        this.coordinator = coordinator;
    }

    public List<UUID> getStudents() {
        return students;
    }

    public void setStudents(List<UUID> students) {
        this.students = students;
    }
}
