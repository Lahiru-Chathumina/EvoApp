package edu.icet.repository.supplier;

import edu.icet.entity.supplier.ProfilePreviousWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfilePreviousWorkRepository extends JpaRepository<ProfilePreviousWorkEntity,Long> {
    @Query("SELECT p FROM ProfilePreviousWorkEntity p WHERE p.supplierId.id = :supplierId")
    List<ProfilePreviousWorkEntity> findBySupplierId(Long supplierId);

}
