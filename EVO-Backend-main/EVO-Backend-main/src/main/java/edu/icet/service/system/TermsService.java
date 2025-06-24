package edu.icet.service.system;

import edu.icet.dto.supplier.Supplier;
import edu.icet.dto.system.Terms;

import java.util.List;
import java.util.Optional;

public interface TermsService {
    Terms addTerms(Terms terms);
    Terms updateTerms(Terms terms);
    Optional<Terms> getTermsById(Integer termId);
    List<Terms> getTermsBySupplier(Supplier supplier);
    void deleteTerms(Integer termId);
    Optional<Terms> getActiveTerms(Supplier supplier);
}