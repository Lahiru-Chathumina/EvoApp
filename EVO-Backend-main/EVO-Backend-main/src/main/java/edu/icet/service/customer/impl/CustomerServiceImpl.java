package edu.icet.service.customer.impl;

import edu.icet.dto.customer.Customer;
import edu.icet.entity.customer.CustomerEntity;
import edu.icet.repository.customer.CustomerRepository;
import edu.icet.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        CustomerEntity savedEntity = customerRepository.save(modelMapper.map(customer, CustomerEntity.class));
        return savedEntity.getEmail().equals(customer.getEmail());
    }

    @Override
    public boolean deleteCustomer(Long id) {

        if (id == null) return false;
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCustomer(Long id, Customer customer) {
        if (id == null || customer == null) return false;
        if (!customerRepository.existsById(id)) return false;
        if (!id.equals(customer.getId())) return false;

        CustomerEntity updatedEntity = customerRepository.save(modelMapper.map(customer, CustomerEntity.class));
        return updatedEntity.getId().equals(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Customer.class))
                .orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(entity -> customers.add(modelMapper.map(entity, Customer.class)));
        return customers;
    }

    @Override
    public boolean existsByEmail(String email) {
        try{
            return customerRepository.existsByEmail(email);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerEntity entity = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                entity.getEmail(),
                entity.getPassword(),
                Collections.emptyList()
        );
    }

    @Override
    public boolean isCustomerExist (String email) {
        return this.customerRepository.existsByEmail(email);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return this.modelMapper.map(this.customerRepository.findByEmail(email), Customer.class);
    }
}
