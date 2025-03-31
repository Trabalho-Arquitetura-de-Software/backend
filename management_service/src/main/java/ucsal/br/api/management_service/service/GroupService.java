package ucsal.br.api.management_service.service;

import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.GroupRepository;
import ucsal.br.api.management_service.repository.IUserRepository;
import ucsal.br.api.management_service.utils.exception.UserNotFoundException;
import ucsal.br.api.management_service.utils.exception.GroupNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final IUserRepository userRepository;

    public GroupService(GroupRepository groupRepository, IUserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public GroupDTO save(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        UserEntity coordinator = userRepository.findById(coordinatorId)
                .orElseThrow(() -> new UserNotFoundException("Coordenador com ID " + coordinatorId + " não encontrado"));

        List<UserEntity> students = userRepository.findAllById(studentIds);

        if (students.size() != studentIds.size()) {
            throw new UserNotFoundException("Um ou mais estudantes não foram encontrados");
        }

        GroupEntity group = new GroupEntity(name, availableForProjects, coordinator, students);

        return new GroupDTO(groupRepository.save(group));
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(GroupDTO::new)
                .collect(Collectors.toList());
    }

    public GroupDTO getGroupById(UUID id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Grupo com ID " + id + " não encontrado"));
        return new GroupDTO(group);
    }

    public GroupDTO update(UUID id, String name, Boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Grupo com ID " + id + " não encontrado"));

        if (name != null) {
            group.setName(name);
        }

        if (availableForProjects != null) {
            group.setAvailableForProjects(availableForProjects);
        }

        if (coordinatorId != null) {
            UserEntity coordinator = userRepository.findById(coordinatorId)
                    .orElseThrow(() -> new UserNotFoundException("Coordenador com ID " + coordinatorId + " não encontrado"));
            group.setCoordinator(coordinator);
        }

        if (studentIds != null && !studentIds.isEmpty()) {
            List<UserEntity> students = userRepository.findAllById(studentIds);
            if (students.size() != studentIds.size()) {
                throw new UserNotFoundException("Um ou mais estudantes não foram encontrados");
            }
            group.setStudents(students);
        }

        return new GroupDTO(groupRepository.save(group));
    }

    public GroupDTO delete(UUID id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Grupo com ID " + id + " não encontrado"));
        groupRepository.delete(group);
        return new GroupDTO(group);
    }
}
