package edu.icet.controller.system;

import edu.icet.dto.system.Review;

import edu.icet.service.system.ReviewService;
import edu.icet.util.RatingType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/addReview")
    public ResponseEntity<Review> addByReview(@Valid @RequestBody Review review) {

        Review savedReview = reviewService.addReview(review);
        return (savedReview != null)
                ? ResponseEntity.status(HttpStatus.CREATED).body(savedReview)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/searchByIdReview/{id}")
    public ResponseEntity<Review> searchByReview(@PathVariable Long id) {
        Review review = reviewService.SearchByReviewID(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateByReview/{id}")
    public Boolean updateReview(@PathVariable Long id, @RequestBody Review review){
        return reviewService.updateReviewById(id, review);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Review>> getAllByReview() {
        List<Review> reviews = reviewService.getAll();

        return reviews.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.ok(reviews);
    }


    @DeleteMapping("/deleteByIdReview/{id}")
    public ResponseEntity<String> deleteByReview(@PathVariable Long id) {
        boolean isDeleted = reviewService.deleteByReview(id);

        return isDeleted
                ? ResponseEntity.ok("Review deleted successfully!")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
    }

    @GetMapping("/SearchBySupplierId/{id}")
     public List<Review>  searchBySupplierID(@PathVariable Long id){
        return  reviewService.getReviewsBySupplierId(id);

    }

    @GetMapping("/getByReviewDate/{date}")
    public  List<Review> getReviewByDate(@PathVariable LocalDate date){
        return  reviewService.getReviewsByDate(date);


    }
    @GetMapping("SearchByReviewType/{ratingType}")
    public List<Review>searchByReviewType(@PathVariable RatingType ratingType ) {
        List<Review> reviews = reviewService.getReviewsByRateType(ratingType);
        if (reviews == null || reviews.isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found Review Type");
        }
        return reviews;
    }



}
