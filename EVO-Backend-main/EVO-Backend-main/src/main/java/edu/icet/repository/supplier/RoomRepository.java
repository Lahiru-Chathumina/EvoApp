package edu.icet.repository.supplier;

import edu.icet.entity.supplier.PropertyEntity;
import edu.icet.entity.supplier.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity,Long>{
    List<RoomEntity> findByBeds(Integer beds);
}
