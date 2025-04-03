package ucsal.br.api.management_service.service;

import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.IGroupRepository;
import ucsal.br.api.management_service.repository.IUserRepository;
import ucsal.br.api.management_service.utils.exception.GroupNotFoundException;
import ucsal.br.api.management_service.utils.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    private final IGroupRepository groupRepository;
    private final IUserRepository userRepository;

    public GroupService(IGroupRepository groupRepository, IUserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public GroupDTO saveGroup(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        UserEntity coordinator = userRepository.findById(coordinatorId)
                .orElseThrow(() -> new UserNotFoundException("Coordinator with ID " + coordinatorId + " not found"));

        List<UserEntity> students = userRepository.findAllById(studentIds);
        if (students.size() != studentIds.size()) {
            throw new UserNotFoundException("One or more students were not found");
        }

        GroupEntity group = new GroupEntity(name, availableForProjects, coordinatorId, studentIds);
        GroupEntity saved = groupRepository.save(group);

        return new GroupDTO(saved, coordinator, students);
    }

    public List<GroupDTO> findAllGroups() {
        return groupRepository.findAll().stream()
                .map(entity -> {
                    UserEntity coordinator = userRepository.findById(entity.getCoordinatorId())
                            .orElseThrow(() -> new UserNotFoundException("Coordinator not found for group " + entity.getId()));

                    List<UserEntity> students = userRepository.findAllById(entity.getStudentsIds());

                    return new GroupDTO(entity, coordinator, students);
                }).toList();
    }

    public GroupDTO findGroupById(UUID id) {
        GroupEntity entity = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        UserEntity coordinator = userRepository.findById(entity.getCoordinatorId())
                .orElseThrow(() -> new UserNotFoundException("Coordinator not found for group " + id));

        List<UserEntity> students = userRepository.findAllById(entity.getStudentsIds());

        return new GroupDTO(entity, coordinator, students);
    }

    public GroupDTO updateGroup(UUID id, String name, Boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        if (name != null) group.setName(name);
        if (availableForProjects != null) group.setAvailableForProjects(availableForProjects);

        if (coordinatorId != null) {
            userRepository.findById(coordinatorId)
                    .orElseThrow(() -> new UserNotFoundException("Coordinator with ID " + coordinatorId + " not found"));
            group.setCoordinatorId(coordinatorId);
        }

        if (studentIds != null && !studentIds.isEmpty()) {
            List<UserEntity> students = userRepository.findAllById(studentIds);
            if (students.size() != studentIds.size()) {
                throw new UserNotFoundException("One or more students were not found");
            }
            group.setStudentsIds(studentIds);
        }

        GroupEntity updated = groupRepository.save(group);

        UserEntity coordinator = userRepository.findById(updated.getCoordinatorId())
                .orElseThrow(() -> new UserNotFoundException("Coordinator not found after update"));

        List<UserEntity> students = userRepository.findAllById(updated.getStudentsIds());

        return new GroupDTO(updated, coordinator, students);
    }

    public GroupDTO deleteGroup(UUID id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        UserEntity coordinator = userRepository.findById(group.getCoordinatorId())
                .orElseThrow(() -> new UserNotFoundException("Coordinator not found for group " + id));

        List<UserEntity> students = userRepository.findAllById(group.getStudentsIds());

        groupRepository.delete(group);

        return new GroupDTO(group, coordinator, students);
    }

    public GroupDTO findGroupByName(String name) {
        GroupEntity group = groupRepository.findByName(name)
                .orElseThrow(() -> new GroupNotFoundException("Group with name '" + name + "' not found"));

        UserEntity coordinator = userRepository.findById(group.getCoordinatorId())
                .orElseThrow(() -> new UserNotFoundException("Coordinator not found for group " + name));

        List<UserEntity> students = userRepository.findAllById(group.getStudentsIds());

        return new GroupDTO(group, coordinator, students);
    }
}
