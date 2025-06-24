package edu.icet.entity.system;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @NotBlank(message = "Title is required.")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Message cannot be empty")
    @Column(nullable = false)
    private String message;

    @NotNull(message = "Type is required")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "Status is required")
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @PastOrPresent(message = "Sent date cannot be in the future")
    private LocalDate sentAt;

    @PastOrPresent(message = "Sent date cannot be in the future")
    private LocalDate readAt;

    @NotNull(message = "User Id is required")
    @Column(nullable = false)
    private Long userId;

    @NotNull(message = "User Type is required")
    @Column(nullable = false)
    private String userType;
    
    private String deliveryMethod;
}
