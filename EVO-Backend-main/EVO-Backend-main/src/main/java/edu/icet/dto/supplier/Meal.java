package edu.icet.dto.supplier;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal {
    private Long mealId;
    private Long cateringId;
    private String name;
    private Double pricePerPerson;
    private String mealType;
}