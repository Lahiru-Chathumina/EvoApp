package edu.icet.entity.supplier;


import edu.icet.util.MealType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Meals")
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catering_Id")
    private CateringEntity cateringId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double pricePerPerson;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

}
