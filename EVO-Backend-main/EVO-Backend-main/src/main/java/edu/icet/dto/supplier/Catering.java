package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catering {
    private Long cateringId;

    private Boolean isAvailable;

    private List<Meal> meals;

    private Long supplierId;
}