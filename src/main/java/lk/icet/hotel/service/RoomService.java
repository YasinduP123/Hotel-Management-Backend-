package lk.icet.hotel.service;

import lk.icet.hotel.dto.Room;

import java.util.List;

public interface RoomService {
	void saveRoom(Room room);

	List<Room> getAll();

	List<Room> findById(Long id);

	void deleteById(Long roomId);

	void update(Room room);
}
