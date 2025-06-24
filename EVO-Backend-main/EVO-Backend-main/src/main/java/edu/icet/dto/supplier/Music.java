package edu.icet.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private Long musicID;

    @NotBlank(message = "Team Size may not be empty")
    private Integer teamSize;

    private Long supplierId;
}