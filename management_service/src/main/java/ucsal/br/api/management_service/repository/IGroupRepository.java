package ucsal.br.api.management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucsal.br.api.management_service.entity.GroupEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IGroupRepository extends JpaRepository<GroupEntity, UUID> {
    List<GroupEntity> findAllByNameIn(List<String> names);
    GroupEntity findByName(String name);

    @Query(
            value = "SELECT * FROM groups g WHERE g.coordinator_id = :coordinatorId",
            nativeQuery = true
    )
    List<GroupEntity> findAllByCoordinatorId(@Param("coordinatorId") UUID coordinatorId);

    @Query(
            value = "SELECT * FROM groups g JOIN group_students gs ON g.id = gs.group_id WHERE gs.student_id = :studentId",
            nativeQuery = true
    )
    List<GroupEntity> findAllByStudentId(@Param("studentId") UUID studentId);
}
