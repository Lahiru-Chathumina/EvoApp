package edu.icet.repository.supplier;

import edu.icet.entity.supplier.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<PropertyEntity,Long> {
}
