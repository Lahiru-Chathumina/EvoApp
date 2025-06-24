package edu.icet.controller.auth;

import edu.icet.dto.admin.Admin;
import edu.icet.dto.auth.JwtResponse;
import edu.icet.dto.auth.LoginRequest;
import edu.icet.dto.customer.Customer;
import edu.icet.dto.supplier.Supplier;
import edu.icet.security.CustomUserDetailsService;
import edu.icet.security.JwtUtil;
import edu.icet.service.admin.AdminService;
import edu.icet.service.customer.CustomerService;
import edu.icet.service.supplier.SupplierManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final SupplierManager supplierService;
    private final AdminService adminService;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
        if (customerService.existsByEmail(customer.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody Admin admin) {
        if (adminService.existsByEmail(admin.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.addAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/register-supplier")
    public ResponseEntity<?> registerSupplier(@Valid @RequestBody Supplier supplier) {
        if (supplierService.existsByEmail(supplier.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        supplierService.addSupplier(supplier);
        return ResponseEntity.ok("Supplier registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        final String role;
        final Object entity;
        final String email = loginRequest.getEmail();

        if (customerService.existsByEmail(email)) {
            role = "CUSTOMER";
            entity = modelMapper.map(customerService.getCustomerByEmail(email), Customer.class);
        } else if (supplierService.existsByEmail(email)) {
            role = "SUPPLIER";
            entity = modelMapper.map(supplierService.getSupplierByEmail(email), Supplier.class);
        } else if (adminService.existsByEmail(email)) {
            role = "ADMIN";
            entity = modelMapper.map(adminService.getAdminByEmail(email), Admin.class);
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String jwtToken = jwtUtil.generateToken(userDetails, role, entity);

        return ResponseEntity.ok(new JwtResponse(email, jwtToken, entity));
    }
}