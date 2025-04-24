package ucsal.br.api.management_service.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.ProjectDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.ProjectEntity;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.IGroupRepository;
import ucsal.br.api.management_service.repository.IProjectRepository;
import ucsal.br.api.management_service.repository.IUserRepository;
import ucsal.br.api.management_service.utils.exception.GroupAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.GroupNotFoundException;
import ucsal.br.api.management_service.utils.exception.UserNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static ucsal.br.api.management_service.service.ProjectService.getProjectDTO;

@Service
public class GroupService {

    private final IGroupRepository groupRepository;
    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;

    public GroupService(IGroupRepository groupRepository, IUserRepository userRepository, IProjectRepository projectRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    private GroupDTO getGroupDto(GroupEntity group) {
        Set<UUID> allUserIds = new HashSet<>();

        if (group.getCoordinator() != null) {
            allUserIds.add(group.getCoordinator());
        }

        if (group.getStudents() != null) {
            allUserIds.addAll(group.getStudents());
        }

        List<UserEntity> userEntities = userRepository.findAllById(allUserIds);
        Map<UUID, UserDTO> userDTOMap = userEntities.stream()
                .collect(Collectors.toMap(UserEntity::getId, UserDTO::new));

        UserDTO coordinatorDTO = userDTOMap.get(group.getCoordinator());

        List<UserDTO> students = (group.getStudents() != null)
                ? group.getStudents().stream()
                .map(userDTOMap::get)
                .filter(Objects::nonNull)
                .toList()
                : Collections.emptyList();

        List<ProjectEntity> projects = projectRepository.findAllByGroup(group.getId());
        List<ProjectDTO> projectDTOS = projects.stream()
                .map(this::createProjectDTO)
                .toList();

        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.isAvailableForProjects(),
                coordinatorDTO,
                students,
                projectDTOS
        );
    }

    private ProjectDTO createProjectDTO(ProjectEntity projectEntity) {
        return getProjectDTO(projectEntity, userRepository, groupRepository);
    }

    public List<GroupDTO> findAllGroups() {
        List<GroupEntity> groupEntities = groupRepository.findAll();
        return groupEntities.stream()
                .map(this::getGroupDto)
                .toList();
    }

    public List<GroupDTO> findAllGroupsById(List<UUID> id) {
        List<GroupEntity> groupEntities = groupRepository.findAllById(id);
        return groupEntities.stream()
                .map(this::getGroupDto)
                .toList();
    }

    public List<GroupDTO> findAllGroupsByNameIn(List<String> names) {
        List<GroupEntity> groupEntities = groupRepository.findAllByNameIn(names);
        return groupEntities.stream()
                .map(this::getGroupDto)
                .toList();
    }

    public List<GroupDTO> findAllGroupsByCoordinatorId(UUID coordinatorId) {
        List<GroupEntity> groupEntities = groupRepository.findAllByCoordinatorId(coordinatorId);

        if (groupEntities.isEmpty()) {
            throw new GroupNotFoundException("No groups found for coordinator ID " + coordinatorId);
        }

        return groupEntities.stream()
                .map(this::getGroupDto)
                .toList();
    }

    public List<GroupDTO> findAllGroupsByStudentId(UUID studentId) {
        List<GroupEntity> groupEntities = groupRepository.findAllByStudentId(studentId);

        if (groupEntities.isEmpty()) {
            throw new GroupNotFoundException("No groups found for student ID " + studentId);
        }

        return groupEntities.stream()
                .map(this::getGroupDto)
                .toList();
    }

    public GroupDTO findGroupByName(String groupName) {
        GroupEntity group = groupRepository.findByName(groupName);
        return getGroupDto(group);
    }

    public GroupDTO saveGroup(GroupDTO groupDTO) {
        if (userRepository.findById(groupDTO.getCoordinator().getId()).isEmpty())
            throw new UserNotFoundException("Coordinator not found for group " + groupDTO.getCoordinator());

        List<String> names = new ArrayList<>();
        names.add(groupDTO.getName());
        Optional<GroupEntity> existingGroup = groupRepository.findAllByNameIn(names).stream().findFirst();
        if (existingGroup.isPresent()) {
            throw new GroupAlredyExistsException("Group with name " + groupDTO.getName() + " already exists.");
        }
        List<UserEntity> students = userRepository.findAllById(
                groupDTO.getStudents().stream()
                        .map(UserDTO::getId)
                        .collect(Collectors.toList())
        );

        if (students.size() != groupDTO.getStudents().size())
            throw new UserNotFoundException("One or more students were not found");

        GroupEntity group = new GroupEntity(groupDTO);
        group = groupRepository.save(group);

        return getGroupDto(group);
    }

    public GroupDTO updateGroup(UUID id, String name, Boolean availableForProjects, UUID coordinator, List<UUID> students) {
        GroupEntity existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        if (coordinator == null && students == null) {
            if (name != null) existingGroup.setName(name);
            if (availableForProjects != null) existingGroup.setAvailableForProjects(availableForProjects);

            GroupEntity updatedGroup = groupRepository.save(existingGroup);
            return getGroupDto(updatedGroup);
        } else {
            GroupEntity newGroup = new GroupEntity();
            newGroup.setId(existingGroup.getId());
            newGroup.setName(name != null ? name : existingGroup.getName());
            newGroup.setAvailableForProjects(availableForProjects != null ? availableForProjects : existingGroup.isAvailableForProjects());

            UUID coordinatorId = coordinator != null ? coordinator : existingGroup.getCoordinator();
            if (coordinatorId != null && !userRepository.existsById(coordinatorId)) {
                throw new IllegalArgumentException("Coordinator with ID " + coordinatorId + " does not exist.");
            }
            newGroup.setCoordinator(coordinatorId);

            List<UUID> studentIds = (students != null && !students.isEmpty())
                    ? students.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList())
                    : existingGroup.getStudents();
            newGroup.setStudents(studentIds);

            GroupEntity savedGroup = groupRepository.save(newGroup);
            return getGroupDto(savedGroup);
        }
    }

    public GroupDTO addStudent(UUID groupId, String studentEmail) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));

        UserEntity student = userRepository.findByEmail(studentEmail);

        if (student == null)
            throw new UserNotFoundException("Student with Email " + studentEmail + " not found");

        List<UUID> students = group.getStudents();
        students.add(student.getId());
        group.setStudents(students);

        GroupEntity updatedGroup = groupRepository.save(group);
        return getGroupDto(updatedGroup);
    }

    public GroupDTO removeStudent(UUID groupId, UUID studentId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + groupId + " not found"));

        if (userRepository.findById(studentId).isEmpty())
            throw new UserNotFoundException("Student with ID " + studentId + " not found");

        List<UUID> students = group.getStudents();
        students.remove(studentId);
        group.setStudents(students);

        GroupEntity updatedGroup = groupRepository.save(group);
        return getGroupDto(updatedGroup);
    }

    public GroupDTO deleteGroup(UUID id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        groupRepository.deleteById(id);

        return new GroupDTO(group);
    }
}
