package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.utils.type.ProjectStatus;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @PrimaryKeyJoinColumn
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String objective;

    @Column(nullable = false)
    private String summaryScope;

    @Column(nullable = false)
    private String targetAudience;

    @Column(nullable = false)
    private LocalDate expectedStartDate;

    @Column(nullable = false)
    private ProjectStatus status;

    @Column(name = "requester_id", nullable = false)
    private UUID requester;

    @Column(name = "group_id", nullable = false)
    private UUID group;

    public ProjectEntity() {}

    public ProjectEntity(String name, String objective, String summaryScope, String targetAudience, LocalDate expectedStartDate, ProjectStatus status, UUID requester, UUID group) {
        this.name = name;
        this.objective = objective;
        this.summaryScope = summaryScope;
        this.targetAudience = targetAudience;
        this.expectedStartDate = expectedStartDate;
        this.status = status;
        this.requester = requester;
        this.group = group;
    }

    public ProjectEntity(ProjectDTO projectDTO) {
        this.id = projectDTO.getId();
        this.name = projectDTO.getName();
        this.objective = projectDTO.getObjective();
        this.summaryScope = projectDTO.getSummaryScope();
        this.targetAudience = projectDTO.getTargetAudience();
        this.expectedStartDate = projectDTO.getExpectedStartDate();
        this.status = projectDTO.getStatus();
        this.requester = projectDTO.getRequester().getId();
        this.group = projectDTO.getGroup().getId();
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSummaryScope() {
        return summaryScope;
    }

    public void setSummaryScope(String summaryScope) {
        this.summaryScope = summaryScope;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public LocalDate getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(LocalDate expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public UUID getRequester() {
        return requester;
    }

    public void setRequester(UUID requester) {
        this.requester = requester;
    }

    public UUID getGroup() {
        return group;
    }

    public void setGroup(UUID group) {
        this.group = group;
    }
}
