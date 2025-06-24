package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.Supplier;
import edu.icet.entity.customer.UserEntity;
import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.repository.customer.UserRepository;
import edu.icet.repository.supplier.SupplierRepository;
import edu.icet.service.supplier.SupplierService;
import edu.icet.util.SupplierCategoryType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository
                .findAll()
                .stream()
                .map(supplier -> mapper.map(supplier, Supplier.class))
                .toList();
    }

    @Override
    public List<Supplier> getSupplierByCategory(SupplierCategoryType category) {
        return getAllSuppliers()
                .stream()
                .filter(supplier -> supplier.getCategory() == category).toList();
    }

    @Override
    public Supplier searchSupplier(Long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id).orElse(null);

        return supplierEntity != null ? mapper.map(supplierEntity, Supplier.class) : null;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        if (supplierRepository.existsById(supplier.getId())) {
            return mapper.map(supplierRepository.save(mapper.map(supplier, SupplierEntity.class)), Supplier.class);

        }

        throw new IllegalArgumentException("Supplier does not exist!");
    }

    @Override
    public Boolean deleteSupplier(Long supplierID) {
        if (supplierRepository.existsById(supplierID)) {

            UserEntity userEntity = userRepository.findBySupplier(supplierRepository.findById(supplierID).orElse(null));
            userRepository.deleteById(userEntity.getUserId());

            supplierRepository.deleteById(supplierID);

            return true;
        }

        throw new IllegalArgumentException("Supplier does not exist!");

    }

}
