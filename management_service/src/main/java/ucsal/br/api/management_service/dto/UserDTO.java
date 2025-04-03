package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.List;
import java.util.UUID;

public class GroupDTO {

    private UUID id;
    private String name;
    private Boolean availableForProjects;
    private UUID coordinatorId;
    private List<UUID> studentsIds;

    public GroupDTO() {}

    public GroupDTO(String name, Boolean availableForProjects, UUID coordinatorId, List<UUID> studentsIds) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinatorId = coordinatorId;
        this.studentsIds = studentsIds;
    }

    public GroupDTO(UUID id, String name, Boolean availableForProjects, UUID coordinatorId, List<UUID> studentsIds) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinatorId = coordinatorId;
        this.studentsIds = studentsIds;
    }

    public GroupDTO(GroupEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.availableForProjects = entity.isAvailableForProjects();
        this.coordinatorId = entity.getCoordinatorId();
        this.studentsIds = entity.getStudentsIds();
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
