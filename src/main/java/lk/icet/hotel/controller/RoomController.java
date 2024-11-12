package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Room;
import lk.icet.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
@CrossOrigin
public class RoomController {

	private final RoomService roomService;

	@PostMapping("/save")
	public void save(@RequestBody Room room){
		roomService.saveRoom(room);
	}

	@GetMapping("/all")
	public List<Room> getAll(@RequestParam(required = false) Long id , Integer roomNumber){

		if(id != null){
			return roomService.findById(id);
		}else if(roomNumber != null){
			return roomService.findByRoomNumber(roomNumber);
		}

		List<Room> all = roomService.getAll();
		log.info(all.toString());
		return all;
	}

	@DeleteMapping("/delete/{roomId}")
	public void delete(@PathVariable Long roomId){

		if(roomId != null){
			roomService.deleteById(roomId);
		}

	}

	@PutMapping("update")
	public void update(@RequestBody Room room){

		if(room != null){
			roomService.update(room);
		}

	}




}
