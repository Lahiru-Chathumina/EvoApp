package edu.icet.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageFeature {
    private Long featureID;

    @NotBlank
    @NotNull(message="featureName cannot be null")
    private String featureName;

    @NotNull(message ="featurePrice cannot be null" )
    private Double featurePrice;
}