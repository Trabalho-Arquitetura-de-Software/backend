package ucsal.br.api.management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.GroupDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.GroupEntity;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.GroupRepository;
import ucsal.br.api.management_service.repository.IUserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private IUserRepository userRepository;

    public GroupDTO save(String name, boolean availableForProjects, UUID coordinatorId, List<UUID> studentIds) {
        // Buscar coordenador pelo ID
        UserEntity coordinator = userRepository.findById(coordinatorId)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador com ID " + coordinatorId + " não encontrado"));

        // Buscar lista de estudantes
        List<UserEntity> students = userRepository.findAllById(studentIds);

        if (students.size() != studentIds.size()) {
            throw new IllegalArgumentException("Um ou mais estudantes não foram encontrados");
        }

        // Criar entidade
        GroupEntity group = new GroupEntity(name, availableForProjects, coordinator, students);

        // Salvar no banco
        GroupEntity saved = groupRepository.save(group);

        // Retornar DTO
        return convertToDTO(saved);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GroupDTO convertToDTO(GroupEntity entity) {
        GroupDTO dto = new GroupDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAvailableForProjects(entity.isAvailableForProjects());

        if (entity.getCoordinator() != null) {
            UserDTO coord = new UserDTO();
            coord.setId(entity.getCoordinator().getId());
            coord.setName(entity.getCoordinator().getName());
            coord.setEmail(entity.getCoordinator().getEmail());
            coord.setRole(entity.getCoordinator().getRole());
            dto.setCoordinator(coord);
        }

        List<UserDTO> studentDTOs = entity.getStudents().stream().map(s -> {
            UserDTO student = new UserDTO();
            student.setId(s.getId());
            student.setName(s.getName());
            student.setEmail(s.getEmail());
            student.setRole(s.getRole());
            return student;
        }).collect(Collectors.toList());


        dto.setStudents(studentDTOs);
        return dto;
    }
}
