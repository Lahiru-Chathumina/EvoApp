
package edu.icet.entity.event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="BeautyPackages")

public class BeautyPackageEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    @NotBlank (message = "Name is required")
    private String name;

    @Column(nullable = false)
    private Double price;
}