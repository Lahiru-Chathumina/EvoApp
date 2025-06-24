package edu.icet.dto.system;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Notification {

    @NotBlank
    private Long notificationId;
    @Size(max = 150, message = "Title must be less than 150 characters")
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private String type;
    @NotNull
    private String status;
    @PastOrPresent(message = "Sent date cannot be in the future")
    private LocalDate sentAt;
    @PastOrPresent(message = "Sent date cannot be in the future")
    private LocalDate readAt;
    @NotBlank(message = "Please provide the delivery method")
    private String deliveryMethod;
    @NotNull
    private Long userId;
    @NotNull
    private String userType;


}
