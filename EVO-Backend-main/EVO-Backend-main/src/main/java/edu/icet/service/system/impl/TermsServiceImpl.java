package edu.icet.service.system.impl;

import edu.icet.dto.supplier.Supplier;
import edu.icet.dto.system.Terms;
import edu.icet.service.system.TermsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TermsServiceImpl implements TermsService {
    final List<Terms> termsList = new ArrayList<>();

    @Override
    public Terms addTerms(Terms terms) {
        termsList.add(terms);
        return terms;
    }

    @Override
    public Terms updateTerms(Terms terms) {
        deleteTerms(terms.getTermId().intValue());
        termsList.add(terms);
        return terms;
    }

    @Override
    public Optional<Terms> getTermsById(Integer termId) {
        return termsList.stream()
                .filter(terms -> terms.getTermId().equals(termId.longValue()))
                .findFirst();
    }

    @Override
    public List<Terms> getTermsBySupplier(Supplier supplier) {
//        return termsList.stream()
//                .filter(terms -> terms.getCategory() == supplier.getCategory())
//                .collect(Collectors.toList());

        return null;
    }

    @Override
    public void deleteTerms(Integer termId) {
        termsList.removeIf(terms -> terms.getTermId().equals(termId.longValue()));
    }

    @Override
    public Optional<Terms> getActiveTerms(Supplier supplier) {
//        return termsList.stream()
//                .filter(terms -> terms.getCategory() == supplier.getCategory() && terms.getIsActive())
//                .findFirst();

        return null;
    }
}

