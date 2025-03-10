package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucsal.br.api.management_service.entity.ProfessorEntity;

import java.util.UUID;

@Repository
public interface IProfessorRepository extends JpaRepository<ProfessorEntity, UUID> {
    ProfessorEntity findByRegistrationNumber(Long registrationNumber);
}

