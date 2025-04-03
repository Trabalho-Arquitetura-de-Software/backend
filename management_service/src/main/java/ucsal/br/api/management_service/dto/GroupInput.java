package ucsal.br.api.management_service.dto.input;

import java.util.List;
import java.util.UUID;

public class GroupInput {
    private String name;
    private boolean availableForProjects;
    private UUID coordinatorId;
    private List<UUID> studentIds;

    // Construtor sem parâmetros
    public GroupInput() {}

    // Construtor com parâmetros
    public GroupInput(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinatorId = coordinatorId;
        this.studentIds = studentIds;
    }

    // Getters e Setters
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

    public List<UUID> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<UUID> studentIds) {
        this.studentIds = studentIds;
    }
}
