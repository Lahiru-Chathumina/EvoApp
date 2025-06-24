package edu.icet.dto.system;
import com.fasterxml.jackson.annotation.JsonFormat;
import edu.icet.util.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAdminSupplier {
    @NotBlank(message = "Not be empty")
    private Long mid;
    @NotBlank(message = "Not be empty")
    private Long adminId;
    @NotBlank(message = "Not be empty")
    private Long supplierId;
    @NotNull
    @NotBlank(message = "Content may not be empty")
    private String content;

    @NotNull
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Instant sendTime;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
