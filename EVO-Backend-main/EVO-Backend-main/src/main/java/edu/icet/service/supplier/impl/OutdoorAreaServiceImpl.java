package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.OutdoorArea;
import edu.icet.entity.supplier.OutdoorAreaEntity;
import edu.icet.repository.supplier.OutdoorAreaRepository;
import edu.icet.service.supplier.OutdoorAreaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutdoorAreaServiceImpl implements OutdoorAreaService {
    final ModelMapper mapper;
    final OutdoorAreaRepository outdoorAreaRepository;

    @Override
    public List<OutdoorArea> getAll() {
        return outdoorAreaRepository.findAll()
                .stream()
                .map(outdoorAreaEntity -> mapper.map(outdoorAreaEntity, OutdoorArea.class))
                .toList();
    }

    @Override
    public OutdoorArea save(OutdoorArea outdoorArea) {
        OutdoorAreaEntity entity = mapper.map(outdoorArea, OutdoorAreaEntity.class);
        OutdoorAreaEntity savedEntity = outdoorAreaRepository.save(entity);
        return mapper.map(savedEntity, OutdoorArea.class);
    }

    @Override
    public Boolean delete(Long id) {
        if (outdoorAreaRepository.existsById(id)) {
            outdoorAreaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public OutdoorArea update(OutdoorArea outdoorArea) {
        if (outdoorAreaRepository.existsById(outdoorArea.getId())) {
            OutdoorAreaEntity entity = mapper.map(outdoorArea, OutdoorAreaEntity.class);

            return mapper.map(outdoorAreaRepository.save(entity), OutdoorArea.class);
        }

        throw new IllegalArgumentException("Outdoor Area does not exist!");
    }
}