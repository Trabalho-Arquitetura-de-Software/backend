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
    private boolean avaliaveForProjects;

    @OneToOne(mappedBy = "coodirnator_id",  cascade = {CascadeType.PERSIST})
    private UserEntity coordinator;

    @OneToMany(mappedBy = "student_id", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<UserEntity> students;

    public GroupEntity(UUID id, String name, boolean avaliaveForProjects, UserEntity coordinator, List<UserEntity> students) {
        this.id = id;
        this.name = name;
        this.avaliaveForProjects = avaliaveForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupEntity() {
    }

    public GroupEntity(String name, boolean avaliaveForProjects, UserEntity coordinator, List<UserEntity> students) {
        this.name = name;
        this.avaliaveForProjects = avaliaveForProjects;
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

    public boolean isAvaliaveForProjects() {
        return avaliaveForProjects;
    }

    public void setAvaliaveForProjects(boolean avaliaveForProjects) {
        this.avaliaveForProjects = avaliaveForProjects;
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
