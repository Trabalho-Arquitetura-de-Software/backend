package ucsal.br.api.management_service.controller;

import jakarta.transaction.Transactional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

    @Transactional
    @MutationMapping
    public GroupDTO saveGroup(
            @Argument String name,
            @Argument Boolean availableForProjects,
            @Argument UUID coordinatorId,
            @Argument List<UUID> studentIds
    ) {
        return groupService.saveGroup(name, availableForProjects, coordinatorId, studentIds);
    }

    @QueryMapping
    public List<GroupDTO> findAllGroups() {
        return groupService.findAllGroups();
    }

    @QueryMapping
    public GroupDTO findGroupById(@Argument UUID id) {
        return groupService.findGroupById(id);
    }

    @Transactional
    @MutationMapping
    public GroupDTO updateGroup(
            @Argument UUID id,
            @Argument String name,
            @Argument Boolean availableForProjects,
            @Argument UUID coordinatorId,
            @Argument List<UUID> studentIds
    ) {
        return groupService.updateGroup(id, name, availableForProjects, coordinatorId, studentIds);
    }

    @Transactional
    @MutationMapping
    public GroupDTO deleteGroup(@Argument UUID id) {
        return groupService.deleteGroup(id);
    }
}
