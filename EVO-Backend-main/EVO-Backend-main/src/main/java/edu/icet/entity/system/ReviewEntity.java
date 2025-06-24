package edu.icet.entity.system;

import edu.icet.util.RatingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Long supplierId;

    @Column(nullable = false)
    private Long customerId;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 1000)
    private String reviewText;

    @Enumerated(EnumType.STRING)
    private RatingType rating;
}
