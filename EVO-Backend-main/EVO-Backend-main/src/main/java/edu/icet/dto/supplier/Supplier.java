package edu.icet.dto.supplier;

import edu.icet.util.SupplierCategoryType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private Long id;

    private String businessName;

    private String businessContactNumber;

    private String businessEmail;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Email cannot be empty")
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "password must be at least 8 characters, contain a number, an uppercase letter, and a special character.")
    private String password;

    private String description;

    private Boolean availability;

    private SupplierCategoryType category;

    private String imageUrl;
}