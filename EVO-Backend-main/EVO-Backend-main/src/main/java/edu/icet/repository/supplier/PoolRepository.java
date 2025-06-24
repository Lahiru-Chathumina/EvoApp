package edu.icet.repository.supplier;

import edu.icet.entity.supplier.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PoolRepository extends JpaRepository<PoolEntity,Long>  {
        List<PoolEntity> findBySize(String size);
        List<PoolEntity> findByDepth(Double depth);
}