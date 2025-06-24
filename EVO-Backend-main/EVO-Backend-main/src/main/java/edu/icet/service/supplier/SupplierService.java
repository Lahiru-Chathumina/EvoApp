package edu.icet.service.supplier;

import edu.icet.dto.supplier.Supplier;
import edu.icet.util.SupplierCategoryType;

import java.util.List;

public interface SupplierService {
    // Supplier
    List<Supplier> getAllSuppliers();
    List<Supplier> getSupplierByCategory(SupplierCategoryType category);
    Supplier searchSupplier(Long id);
    Supplier updateSupplier(Supplier supplier);
    Boolean deleteSupplier(Long supplerID);
}
