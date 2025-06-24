package edu.icet.repository.supplier;

import edu.icet.entity.supplier.MealEntity;
import edu.icet.util.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<MealEntity, Long> {
}
