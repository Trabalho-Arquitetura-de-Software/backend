package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    List<UserEntity> findByRole(UserRole role);
}
