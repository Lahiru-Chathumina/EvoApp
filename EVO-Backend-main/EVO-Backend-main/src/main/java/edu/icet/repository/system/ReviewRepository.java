package edu.icet.repository.system;

import edu.icet.dto.system.Review;
import edu.icet.entity.system.ReviewEntity;
import edu.icet.util.RatingType;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    List<ReviewEntity> findBySupplierId(Long id);

    List<ReviewEntity> findByCustomerId(Long customerId);

    List<ReviewEntity> findByDate(LocalDate date);

    List<ReviewEntity> findByRating(RatingType type);
}
