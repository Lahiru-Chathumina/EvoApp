package edu.icet.repository.supplier;

import edu.icet.entity.supplier.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    @Query("SELECT s FROM SupplierEntity s WHERE s.email = :email")
    Optional<SupplierEntity> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SupplierEntity s WHERE s.email = :email")
    boolean existsByEmail(@Param("email") String email);
}