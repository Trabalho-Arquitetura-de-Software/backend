package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucsal.br.api.management_service.entity.ProjectEntity;

import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<ProjectEntity, UUID> {
    ProjectEntity findByName(String name);
}
