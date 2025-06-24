package edu.icet.entity.system;

import edu.icet.util.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MessageAdminSupplier")

public class MessageAdminSupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    @Column(nullable = false)
    private Long adminId;
    @Column(nullable = false)
    private Long supplierId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Instant sendTime;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
