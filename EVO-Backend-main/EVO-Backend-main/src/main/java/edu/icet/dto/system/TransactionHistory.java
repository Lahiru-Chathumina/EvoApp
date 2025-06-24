package edu.icet.dto.system;

import edu.icet.util.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TransactionHistory {

    private Long id;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Payment Type cannot be null")
    private PaymentType type;

    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount cannot be negative")
    private Double amount;

    @NotNull(message = "Confirmation cannot be null")
    private Boolean confirmation;

    @NotNull(message = "Confirmation cannot be null")
    private Long userId;

}