package ucsal.br.api.management_service.dto;

import java.util.List;

public class GroupDTO {
    private Long id;
    private String name;
    private Boolean availableForProjects;
    private UserDTO coordinator;
    private List<UserDTO> students;

    public GroupDTO() {
    }

    public GroupDTO(String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public GroupDTO(Long id, String name, Boolean availableForProjects, UserDTO coordinator, List<UserDTO> students) {
        this.id = id;
        this.name = name;
        this.availableForProjects = availableForProjects;
        this.coordinator = coordinator;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
