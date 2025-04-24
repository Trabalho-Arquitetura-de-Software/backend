package ucsal.br.api.management_service.controller;

import jakarta.transaction.Transactional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.service.GroupService;

import java.util.List;
import java.util.UUID;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @QueryMapping
    public List<GroupDTO> findAllGroups() {
        return groupService.findAllGroups();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @QueryMapping
    public List<GroupDTO> findAllGroupsById(@Argument List<UUID> id) {
        return groupService.findAllGroupsById(id);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @QueryMapping
    public List<GroupDTO> findAllGroupsByNameIn(@Argument List<String> names) {
        return groupService.findAllGroupsByNameIn(names);
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @QueryMapping
    public List<GroupDTO> findAllGroupByCoordinator(@Argument UUID coordinator_id) {
        return groupService.findAllGroupsByCoordinatorId(coordinator_id);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @QueryMapping
    public List<GroupDTO> findAllGroupsByStudentId(@Argument UUID student_id) {
        return groupService.findAllGroupsByStudentId(student_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @MutationMapping
    public GroupDTO saveGroup(@Argument String name, @Argument UUID coordinator, @Argument List<UUID> students) {
        return groupService.saveGroup(new GroupDTO(name, true, coordinator, students));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @MutationMapping
    public GroupDTO updateGroup(@Argument UUID id, @Argument String name, @Argument Boolean availableForProjects, @Argument UUID coordinator, @Argument List<UUID> students) {
        return groupService.updateGroup(id, name, availableForProjects, coordinator, students);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @MutationMapping
    public GroupDTO deleteGroup(@Argument UUID id) {
        return groupService.deleteGroup(id);
    }
}
