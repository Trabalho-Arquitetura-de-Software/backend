package ucsal.br.api.management_service.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.service.ProjectService;
import ucsal.br.api.management_service.utils.type.ProjectStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<ProjectDTO> findAllProjects() {
        return projectService.findAllProjects();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @MutationMapping
    public ProjectDTO saveProject(
            @Argument String name,
            @Argument String objective,
            @Argument String summaryScope,
            @Argument String targetAudience,
            @Argument LocalDate expectedStartDate,
            @Argument UUID requester
    ) {
        return projectService.saveProject(new ProjectDTO(
                name,
                objective,
                summaryScope,
                targetAudience,
                expectedStartDate,
                ProjectStatus.PENDING_ANALYSIS,
                requester,
                null
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public ProjectDTO updateProject(
            @Argument UUID id,
            @Argument String name,
            @Argument String objective,
            @Argument String summaryScope,
            @Argument String targetAudience,
            @Argument LocalDate expectedStartDate,
            @Argument ProjectStatus status,
            @Argument UUID requester)
    {
        return projectService.updateProject(new ProjectDTO(
                id,
                name,
                objective,
                summaryScope,
                targetAudience,
                expectedStartDate,
                status,
                requester,
                null
        ));
    }
}