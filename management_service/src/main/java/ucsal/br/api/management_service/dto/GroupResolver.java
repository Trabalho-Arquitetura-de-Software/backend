package ucsal.br.api.management_service.resolver;

import jakarta.transaction.Transactional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.input.GroupInput;
import ucsal.br.api.management_service.service.GroupService;

import java.util.List;
import java.util.UUID;

@Controller
public class GroupResolver {

    private final GroupService groupService;

    public GroupResolver(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Mutation para salvar um novo grupo
     */
    @Transactional
    @MutationMapping
    public GroupDTO saveGroup(@Argument GroupInput input) {
        return groupService.save(
                input.getName(),
                input.isAvailableForProjects(),
                input.getCoordinatorId(),
                input.getStudentIds()
        );
    }

    /**
     * Query para buscar todos os grupos
     */
    @QueryMapping
    public List<GroupDTO> findAllGroups() {
        return groupService.getAllGroups();
    }

    /**
     * Query para buscar um grupo por ID
     */
    @QueryMapping
    public GroupDTO findGroupById(@Argument UUID id) {
        return groupService.getGroupById(id);
    }

    /**
     * Mutation para atualizar um grupo
     */
    @Transactional
    @MutationMapping
    public GroupDTO updateGroup(
            @Argument UUID id,
            @Argument String name,
            @Argument Boolean availableForProjects,
            @Argument UUID coordinatorId,
            @Argument List<UUID> studentIds
    ) {
        return groupService.update(id, name, availableForProjects, coordinatorId, studentIds);
    }

    /**
     * Mutation para deletar um grupo
     */
    @Transactional
    @MutationMapping
    public GroupDTO deleteGroup(@Argument UUID id) {
        return groupService.delete(id);
    }
}
