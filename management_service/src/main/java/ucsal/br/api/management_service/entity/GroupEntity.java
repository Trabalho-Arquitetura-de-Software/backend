package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "GROUPS")
public class GroupEntity {

    @Id
    @PrimaryKeyJoinColumn
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean availableForProjects;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coordinator_id")
    private UserEntity coordinator;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "group_students",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<UserEntity> students;

    public GroupEntity() {
    }

    public GroupEntity(UUID id, String name, boolean availableForProjects, UserEntity coordinator, List<UserEntity> students) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupEntity(String name, boolean availableForProjects, UserEntity coordinator, List<UserEntity> students) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
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

    public UserEntity getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UserEntity coordinator) {
        this.coordinator = coordinator;
    }

    public List<UserEntity> getStudents() {
        return students;
    }

    public void setStudents(List<UserEntity> students) {
        this.students = students;
    }
}
