package ucsal.br.api.management_service.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.input.GroupInput;
import ucsal.br.api.management_service.service.GroupService;
import ucsal.br.api.management_service.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GroupResolver implements GraphQLMutationResolver {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Transactional
    public GroupDTO saveGroup(GroupInput input) {
        return groupService.save(
                input.getName(),
                input.isAvailableForProjects(),
                input.getCoordinatorId(),
                input.getStudentIds()
        );
    }
}
