package edu.icet.repository.supplier;

import edu.icet.dto.supplier.Music;
import edu.icet.entity.supplier.MusicEntity;
import edu.icet.entity.supplier.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<MusicEntity,Long> {

    @Query(value = "SELECT m.* FROM music m WHERE m.supplier_id = :supplierId", nativeQuery = true)
    Optional<MusicEntity> findBySupplierId(@Param("supplierId") Long supplierID);
}
