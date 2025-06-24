package edu.icet.dto.system;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotNull(message = "ID cannot be null")
    @Min(value = 1,message = "ID must be greater than or equal to 1")
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
