package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

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
    private UUID coordinatorId;

    @ElementCollection
    @CollectionTable(name = "group_students", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "student_id")
    private List<UUID> studentsIds;

    public GroupEntity() {}

    public GroupEntity(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentsIds) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinatorId = coordinatorId;
        this.studentsIds = studentsIds;
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

    public UUID getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(UUID coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public List<UUID> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<UUID> studentsIds) {
        this.studentsIds = studentsIds;
    }
}
