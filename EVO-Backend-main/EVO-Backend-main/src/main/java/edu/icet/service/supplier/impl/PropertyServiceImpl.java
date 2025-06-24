package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.Property;
import edu.icet.entity.supplier.PropertyEntity;
import edu.icet.repository.supplier.PropertyRepository;
import edu.icet.service.supplier.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Property> getAll() {
        List<Property> propertyList = new ArrayList<>();
        List<PropertyEntity> all = repository.findAll();

        all.forEach(propertyEntity -> {
            propertyList.add(mapper.map(propertyEntity,Property.class));
        });
        return propertyList;
    }

    @Override
    public Property save(Property property) {
        if(property==null){
            return null;
        }
        PropertyEntity save = repository.save(mapper.map(property,PropertyEntity.class));
        return mapper.map(save,Property.class);
    }

    @Override
    public Property search(Long propertyID) {

        PropertyEntity search = repository.findById(propertyID).orElse(null);
        if (search == null) throw new IllegalArgumentException("Property does not exist!");

        return mapper.map(search, Property.class);
    }


    @Override
    public Boolean delete(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Property update(Property property) {
        if(property==null){
            return null;
        }
        PropertyEntity save = repository.save(mapper.map(property,PropertyEntity.class));
        return mapper.map(save,Property.class);
    }
}
