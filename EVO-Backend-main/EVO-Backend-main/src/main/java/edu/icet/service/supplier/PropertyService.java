package edu.icet.service.supplier;

import edu.icet.dto.supplier.Property;

import java.util.List;


public interface PropertyService {
    List<Property> getAll();
    Property save(Property property);
    Property search(Long propertyID);
    Boolean delete(Long id);
    Property update(Property property);
}
