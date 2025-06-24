package edu.icet.entity.system;


import edu.icet.util.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "MessageCustomerSupplier")
public class MessageCustomerSupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private Long supplierId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Instant sendTime;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
