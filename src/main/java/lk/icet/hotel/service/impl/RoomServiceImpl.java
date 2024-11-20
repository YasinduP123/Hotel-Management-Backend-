package lk.icet.hotel.service.impl;
import lk.icet.hotel.dto.Room;
import lk.icet.hotel.entity.RoomEntity;
import lk.icet.hotel.repository.RoomRepository;
import lk.icet.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	private final ModelMapper mapper;
	@Override
	public void saveRoom(Room room) {
		roomRepository.save(mapper.map(room, RoomEntity.class));
	}

	@Override
	public List<Room> getAll() {
		List<Room> rooms = new ArrayList<>();
		roomRepository.findAll().forEach(roomEntity -> rooms.add(mapper.map(roomEntity,Room.class)));
		return rooms;
	}

	@Override
	public List<Room> findById(Long id) {
		List<Room> rooms = new ArrayList<>();
		rooms.add(mapper.map(roomRepository.findById(id),Room.class));
		return rooms;
	}

	@Override
	public void deleteById(Long roomId) {
		roomRepository.deleteById(roomId);
	}

	@Override
	public void update(Room room) {
		roomRepository.save(mapper.map(room, RoomEntity.class));
	}

	@Override
	public List<Room> findByRoomNumber(Integer roomNumber) {
		List<Room> rooms = new ArrayList<>();

		rooms.add(mapper.map(roomRepository.findByRoomNumber(roomNumber), Room.class));

		return rooms;
	}


}
