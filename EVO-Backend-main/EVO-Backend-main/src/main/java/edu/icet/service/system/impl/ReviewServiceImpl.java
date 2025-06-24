package edu.icet.service.system.impl;

import edu.icet.dto.system.Review;
import edu.icet.entity.system.ReviewEntity;
import edu.icet.repository.system.ReviewRepository;
import edu.icet.service.system.QuickReplyService;
import edu.icet.service.system.ReviewService;
import edu.icet.util.RatingType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    final ReviewRepository reviewRepository;
    final ModelMapper mapper;
    final QuickReplyService service;

    @Override
    public Review addReview(Review review) {
        if (Boolean.FALSE.equals(service.filterProfanity(review.getReviewText()))) {
            return mapper.map(reviewRepository.save(mapper
                    .map(review, ReviewEntity.class)), Review.class);
        } else {
            return null;
        }

    }

    @Override
    public Boolean updateReviewById(Long id, Review review) {
        if (reviewRepository.existsById(id) && id.equals(review.getReviewId())) {
            reviewRepository.save(mapper.map(review, ReviewEntity.class));
            return true;
        }
        return false;
    }

    public List<Review> getAll() {
        if (!reviewRepository.findAll().isEmpty()) {
            return reviewRepository.findAll().stream().map(reviewEntity ->
                    mapper.map(reviewEntity, Review.class)
            ).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deleteByReview(Long id) {
        boolean isExistId = reviewRepository.existsById(id);
        if (isExistId) {
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Review> getReviewsBySupplierId(Long supplierId) {
        return reviewRepository.findBySupplierId(supplierId).stream().map(reviewEntity ->
                mapper.map(reviewEntity, Review.class)).toList();
    }

    public List<Review> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId).stream().map(reviewEntity ->
                mapper.map(reviewEntity, Review.class)).toList();
    }

    @Override
    public List<Review> getReviewsByDate(LocalDate date) {
        return reviewRepository.findByDate(date).stream().map(reviewEntity -> mapper
                .map(reviewEntity, Review.class)).toList();
    }

    @Override
    public List<Review> getReviewsByRateType(RatingType type) {
        return reviewRepository.findAll().stream()
                .filter(reviewEntity -> reviewEntity.getRating().equals(type))
                .map(reviewEntity -> mapper.map(reviewEntity, Review.class))
                .toList();
    }

    @Override
    public Review SearchByReviewID(Long id) {
        Optional<ReviewEntity> reviewEntity = reviewRepository.findById(id);
        return reviewEntity.map(entity -> mapper
                .map(entity, Review.class)).orElse(null);
    }
}
