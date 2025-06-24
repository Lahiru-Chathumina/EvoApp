package edu.icet.service.system;

import edu.icet.dto.system.Review;
import edu.icet.util.RatingType;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    Review addReview(Review review);

    Boolean updateReviewById(Long id, Review review);

    List<Review> getAll();

    boolean deleteByReview(Long id);

    List<Review> getReviewsBySupplierId(Long supplierId);

    List<Review> getReviewsByCustomerId(Long customerId);

    List<Review> getReviewsByDate(LocalDate date);

    List<Review> getReviewsByRateType(RatingType type);

    Review SearchByReviewID(Long id);

}
