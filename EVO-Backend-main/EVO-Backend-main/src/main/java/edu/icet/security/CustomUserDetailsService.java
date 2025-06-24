package edu.icet.security;

import edu.icet.entity.admin.AdminEntity;
import edu.icet.entity.customer.CustomerEntity;
import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.repository.admin.AdminRepository;
import edu.icet.repository.customer.CustomerRepository;
import edu.icet.repository.supplier.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (customerRepository.existsByEmail(email)) {
            CustomerEntity customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));
            return User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles("CUSTOMER")
                    .build();
        } else if (adminRepository.existsByEmail(email)) {
            AdminEntity admin = adminRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));
            return User.withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        } else if (supplierRepository.existsByEmail(email)) {
            SupplierEntity supplier = supplierRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Supplier not found with email: " + email));
            return User.withUsername(supplier.getEmail())
                    .password(supplier.getPassword())
                    .roles("SUPPLIER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}