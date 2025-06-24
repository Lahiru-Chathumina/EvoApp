package edu.icet.repository.supplier;

import edu.icet.entity.event.BeautySaloonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeautySaloonRepository extends JpaRepository<BeautySaloonEntity,Long> {
    Optional<BeautySaloonEntity> findBySupplier_Id(Long supplierID);

    void deleteBySupplierId(Long supplierID);
}