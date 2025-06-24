package edu.icet.entity.customer;

import edu.icet.entity.admin.AdminEntity;
import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.entity.system.UserReportEntity;
import edu.icet.util.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String registeredDate;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotEmpty(message = "Contact Number should not be blank")
    private String contactNumber;

    private String address;

    private String city;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_report_id")
    private UserReportEntity report;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
