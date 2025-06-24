package edu.icet.service.customer;

import edu.icet.dto.customer.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomerService {
    boolean addCustomer (Customer customer);
    boolean deleteCustomer (Long id);
    boolean updateCustomer(Long id,Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();

    boolean existsByEmail(@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email);

    UserDetails loadUserByUsername(String email);
    boolean isCustomerExist (String email);
    Customer getCustomerByEmail (String email);
}
