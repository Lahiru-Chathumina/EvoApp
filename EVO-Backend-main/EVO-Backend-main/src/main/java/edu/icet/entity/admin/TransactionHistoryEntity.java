package edu.icet.entity.admin;

import edu.icet.util.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="transaction_history")
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private Double amount;
    private Boolean confirmation;
    private Long userId;
}
