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
}
