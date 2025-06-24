package edu.icet.repository.admin;

import edu.icet.entity.admin.AdminEntity;
import edu.icet.util.AdminType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
     @Query("SELECT a FROM AdminEntity a WHERE a.email = :email")
     Optional<AdminEntity> findByEmail(@Param("email") String email);

     @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AdminEntity a WHERE a.email = :email")
     boolean existsByEmail(@Param("email") String email);

     Iterable<Object> findAllByType(AdminType type);
}