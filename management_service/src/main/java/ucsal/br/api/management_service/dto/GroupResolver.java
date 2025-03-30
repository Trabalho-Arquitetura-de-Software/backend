package ucsal.br.api.management_service.dto;

import jakarta.transaction.Transactional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.input.GroupInput;
import ucsal.br.api.management_service.service.GroupService;

@Controller
public class GroupResolver {

    private final GroupService groupService;

    public GroupResolver(GroupService groupService) {
        this.groupService = groupService;
    }

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
}
