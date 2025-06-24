package edu.icet.dto.customer;



import com.fasterxml.jackson.annotation.JsonInclude;
import edu.icet.dto.admin.Admin;
import edu.icet.dto.supplier.Supplier;
import edu.icet.util.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @NotNull
    @NotEmpty(message = "User ID should not be blank")
    private Long userId;

    @NotEmpty(message = "First Name should not be blank")
    private String firstName;

    @NotEmpty(message = "Last Name should not be blank")
    private String lastName;

    @NotNull
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    @PastOrPresent(message = "Registered date should be in past or present")
    private String registeredDate;

    @NotEmpty(message = "User type should not be blank")
    private UserType userType;

    @NotEmpty(message = "Contact Number should not be blank")
    private String contactNumber;

    @NotEmpty(message = "Address should not be blank")
    private String address;

    @NotEmpty(message = "City should not be blank")
    private String city;


    private UserReport report;

//    @JsonView(JsonAPIViews.SupplierView.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Supplier supplier;

//    @JsonView(JsonAPIViews.AdminView.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Admin admin;

//    @JsonView(JsonAPIViews.CustomerView.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Customer customer;

}
