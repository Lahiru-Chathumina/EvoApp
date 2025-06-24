package edu.icet.service.supplier;

import edu.icet.dto.supplier.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    Room save(Room room);
    Room search(Long id);
    Boolean delete(Long id);
    Room update(Room room);
}
