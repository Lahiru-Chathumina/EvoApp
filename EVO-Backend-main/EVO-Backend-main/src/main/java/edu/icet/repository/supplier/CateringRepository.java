package edu.icet.repository.supplier;

import edu.icet.dto.supplier.Catering;
import edu.icet.entity.supplier.CateringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface CateringRepository extends JpaRepository <CateringEntity, Long>  {


    @Query(value = "SELECT c.* FROM Catering c WHERE c.supplier_id = :supplierId", nativeQuery = true)
    Optional<CateringEntity> findBySupplierId(@Param("supplierId") Long supplierID);


}
