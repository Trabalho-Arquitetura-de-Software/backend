package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.ProjectEntity;
import ucsal.br.api.management_service.utils.type.ProjectStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ProjectDTO {

    private UUID id;
    private String name;
    private String objective;
    private String summaryScope;
    private String targetAudience;
    private LocalDate expectedStartDate;
    private ProjectStatus status;
    private UserDTO requester;
    private GroupDTO group;

    public ProjectDTO() {
    }

    public ProjectDTO(String name, String objective, String summaryScope, String targetAudience, LocalDate expectedStartDate, ProjectStatus status, UUID requester, UUID group) {
        this.name = name;
        this.objective = objective;
        this.summaryScope = summaryScope;
        this.targetAudience = targetAudience;
        this.expectedStartDate = expectedStartDate;
        this.status = status;

        this.requester = new UserDTO();
        this.requester.setId(requester);

        this.group = new GroupDTO();
        this.group.setId(group);
    }

    public ProjectDTO(UUID id, String name, String objective, String summaryScope, String targetAudience, LocalDate expectedStartDate, ProjectStatus status, UUID requester, UUID group) {
        this.id = id;
        this.name = name;
        this.objective = objective;
        this.summaryScope = summaryScope;
        this.targetAudience = targetAudience;
        this.expectedStartDate = expectedStartDate;
        this.status = status;

        this.requester = new UserDTO();
        this.requester.setId(requester);

        this.group = new GroupDTO();
        this.group.setId(group);
    }

    public ProjectDTO(UUID id, String name, String objective, String summaryScope, String targetAudience, LocalDate expectedStartDate, ProjectStatus status, UserDTO requester, GroupDTO group) {
        this.id = id;
        this.name = name;
        this.objective = objective;
        this.summaryScope = summaryScope;
        this.targetAudience = targetAudience;
        this.expectedStartDate = expectedStartDate;
        this.status = status;
        this.requester = requester;
        this.group = group;
    }

    public ProjectDTO(ProjectEntity projectEntity) {
        this.id = projectEntity.getId();
        this.name = projectEntity.getName();
        this.objective = projectEntity.getObjective();
        this.summaryScope = projectEntity.getSummaryScope();
        this.targetAudience = projectEntity.getTargetAudience();
        this.expectedStartDate = projectEntity.getExpectedStartDate();
        this.status = projectEntity.getStatus();

        this.requester = new UserDTO();
        this.requester.setId(projectEntity.getRequester());

        this.group = new GroupDTO();
        this.group.setId(projectEntity.getGroup());
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

    public UserDTO getRequester() {
        return requester;
    }

    public void setRequester(UserDTO requester) {
        this.requester = requester;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }
}
