package edu.icet.entity.supplier;

import edu.icet.entity.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProfilePreviousWork")
public class ProfilePreviousWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long previousWorkID;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDate completionDate;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customer; // Customers can also be null

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

    private List<String> imageUrl;

}
