package edu.icet.dto.supplier;

import edu.icet.util.HallAvailabilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Hall {
    private Long hallId;

    @NotNull
    @NotBlank
    private Integer count;

    @NotNull
    @NotBlank
    private HallAvailabilityType availability;
}
