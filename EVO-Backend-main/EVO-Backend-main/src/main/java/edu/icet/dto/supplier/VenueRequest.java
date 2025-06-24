package edu.icet.dto.supplier;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenueRequest {
    private Long venueRequestID;

    @NotNull(message="Supplier ID cannot be null")
    private Supplier supplier;

    @NotNull(message="Venue ID cannot be null")
    private Venue venue;

    @NotNull(message="Created DateTime cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    @NotNull(message="Status cannot be null")
    @NotBlank(message="Status cannot be blank")
    private Boolean status;
}
