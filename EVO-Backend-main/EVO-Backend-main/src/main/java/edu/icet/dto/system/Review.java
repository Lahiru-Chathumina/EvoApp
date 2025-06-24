package edu.icet.dto.system;

import edu.icet.util.RatingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
   private Long reviewId;
   private Long supplierId;
   private Long customerId;
   private LocalDate date;
   private String reviewText;
   private RatingType ratingType;
}
