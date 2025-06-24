package edu.icet.repository.customer;

import edu.icet.entity.customer.UserEntity;
import edu.icet.entity.supplier.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findBySupplier(SupplierEntity supplierEntity);
}
