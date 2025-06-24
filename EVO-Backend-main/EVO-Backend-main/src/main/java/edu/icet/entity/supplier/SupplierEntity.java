package edu.icet.entity.supplier;


import edu.icet.util.SupplierCategoryType;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String businessContactNumber;

    private String businessEmail;

    private String description;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Boolean availability;

    @Enumerated(EnumType.STRING)
    private SupplierCategoryType category;

    private String profileImageUrl;
}
