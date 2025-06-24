package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.Room;
import edu.icet.entity.supplier.RoomEntity;
import edu.icet.repository.supplier.RoomRepository;
import edu.icet.service.supplier.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        List<RoomEntity> all = repository.findAll();

        all.forEach(roomEntity -> {
            roomList.add(mapper.map(roomEntity,Room.class));
        });
        return roomList;
    }

    @Override
    public Room save(Room room) {
        if (!repository.existsById(room.getRoomId())){
            return mapper.map(repository.save(mapper.map(room, RoomEntity.class)), Room.class);
        }

        throw new IllegalArgumentException("Room already exists!");
    }

    @Override
    public Room search(Long id) {
        return mapper.map(repository.findById(id),Room.class);
    }



    @Override
    public Boolean delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Room update(Room room) {
        if (room==null){
            return null;
        }
        RoomEntity save = repository.save(mapper.map(room, RoomEntity.class));
        return mapper.map(save, Room.class);
    }
}
