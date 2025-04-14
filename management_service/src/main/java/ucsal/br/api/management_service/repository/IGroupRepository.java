package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IGroupRepository extends JpaRepository<GroupEntity, UUID> {
    List<GroupEntity> findAllByNameIn(List<String> names);
    GroupEntity findByName(String name);
}
