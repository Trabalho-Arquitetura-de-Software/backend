package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
}
