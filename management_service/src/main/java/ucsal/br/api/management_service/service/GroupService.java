package ucsal.br.api.management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.GroupRepository;
import ucsal.br.api.management_service.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public GroupDTO saveGroup(GroupDTO dto) {
        GroupEntity entity = new GroupEntity();
        entity.setName(dto.getName());
        entity.setAvailableForProjects(dto.getAvailableForProjects());

        UserEntity coordinator = new UserEntity();
        coordinator.setId(dto.getCoordinator().getId());
        entity.setCoordinator(coordinator);

        List<UserEntity> students = dto.getStudents().stream().map(s -> {
            UserEntity user = new UserEntity();
            user.setId(s.getId());
            return user;
        }).collect(Collectors.toList());
        entity.setStudents(students);

        GroupEntity saved = groupRepository.save(entity);

        dto.setId(saved.getId().getMostSignificantBits() & Long.MAX_VALUE); // temporário para fins de retorno com ID
        return dto;
    }

    public List<GroupDTO> getAllGroups() {
        List<GroupEntity> entities = groupRepository.findAll();
        return entities.stream().map(entity -> {
            GroupDTO dto = new GroupDTO();
            dto.setId(entity.getId().getMostSignificantBits() & Long.MAX_VALUE);
            dto.setName(entity.getName());
            dto.setAvailableForProjects(entity.isAvailableForProjects());

            UserDTO coordinator = new UserDTO();
            coordinator.setId(entity.getCoordinator().getId());
            dto.setCoordinator(coordinator);

            List<UserDTO> students = entity.getStudents().stream().map(student -> {
                UserDTO studentDTO = new UserDTO();
                studentDTO.setId(student.getId());
                return studentDTO;
            }).collect(Collectors.toList());
            dto.setStudents(students);

            return dto;
        }).collect(Collectors.toList());
    }

    public GroupDTO save(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        UserEntity coordinator = userRepository.findById(coordinatorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        List<UserEntity> students = userRepository.findAllById(studentIds);

        GroupEntity group = new GroupEntity(name, availableForProjects, coordinator, students);

        GroupEntity saved = groupRepository.save(group);

        GroupDTO dto = new GroupDTO();
        dto.setId(saved.getId().getMostSignificantBits() & Long.MAX_VALUE);
        dto.setName(saved.getName());
        dto.setAvailableForProjects(saved.isAvailableForProjects());

        UserDTO coordDTO = new UserDTO();
        coordDTO.setId(coordinator.getId());
        dto.setCoordinator(coordDTO);

        List<UserDTO> studentDTOs = students.stream().map(s -> {
            UserDTO d = new UserDTO();
            d.setId(s.getId());
            return d;
        }).collect(Collectors.toList());
        dto.setStudents(studentDTOs);

        return dto;
    }
}
