package edu.icet.entity.supplier;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Catering")
public class CateringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long cateringId;

    @Column(nullable = false)
    private Boolean isAvailable;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catering_id")
    private List<MealEntity> meals;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;
}
