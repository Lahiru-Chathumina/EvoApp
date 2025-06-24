package edu.icet.dto.customer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @PositiveOrZero(message = "ID must be positive")
    private Long id;

    @NotBlank(message = "firstName cannot be empty")
    @NotNull
    private String firstName;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Email cannot be empty")
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "password must be at least 8 characters, contain a number, an uppercase letter, and a special character.")
    private String password;

    @NotBlank(message = "Phone number cannot be empty")
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String contactNumber;

    @NotBlank(message = "ProfileImageUrl cannot be empty")
    private String profileImageUrl;

    @NotBlank(message = "address cannot be null")
    private String address;

}
