package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.Hall;
import edu.icet.entity.supplier.HallEntity;
import edu.icet.repository.supplier.HallRepository;
import edu.icet.service.supplier.HallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    final ModelMapper modelMapper;
    final HallRepository hallRepository;

    @Override
    public List<Hall> getAll() {
        return hallRepository.findAll()
                .stream()
                .map(hallEntity -> modelMapper.map(hallEntity, Hall.class))
                .toList();
    }

    @Override
    public Hall save(Hall hall) {
        HallEntity entity = modelMapper.map(hall, HallEntity.class);
        HallEntity savedEntity = hallRepository.save(entity);
        return modelMapper.map(savedEntity, Hall.class);
    }

    @Override
    public Hall search(Long hallID) {
        HallEntity entity = hallRepository.findById(hallID).orElse(null);
        return entity != null ? modelMapper.map(entity, Hall.class) : null;
    }

    @Override
    public Boolean delete(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Hall update(Hall hall) {
        if (hallRepository.existsById(hall.getHallId())) {
            HallEntity entity = modelMapper.map(hall, HallEntity.class);
            HallEntity updatedEntity = hallRepository.save(entity);
            return modelMapper.map(updatedEntity, Hall.class);
        }
        return null;
    }
}