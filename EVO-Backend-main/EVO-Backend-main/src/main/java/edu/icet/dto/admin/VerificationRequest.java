package edu.icet.dto.admin;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequest {

    @NotBlank(message = "Request ID is required")
    private Long requestID;
    @FutureOrPresent(message = "Respond date must be today or future")
    private LocalDate responseDate;
    @PastOrPresent(message = "Submission date must be Today")
    private LocalDate submissionDate;
    @NotBlank(message = "Status is cannot be empty")
    private String status;
    @NotBlank(message = "Verification document is must")
    private String verificationDocument;
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email must be valid format")
    private String email;
    @NotBlank(message = "Phone number cannot be null")
    private String phoneNumber;
}
