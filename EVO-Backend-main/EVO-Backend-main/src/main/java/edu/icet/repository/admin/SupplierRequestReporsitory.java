package edu.icet.repository.admin;

import edu.icet.entity.supplier.SupplierRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRequestReporsitory extends JpaRepository<SupplierRequestEntity,Long> {
}
