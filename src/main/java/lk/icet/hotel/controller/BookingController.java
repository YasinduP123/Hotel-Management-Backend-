package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Booking;
import lk.icet.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

	final BookingService bookingService;

	@PostMapping("/save")
	public void setBooking(@RequestBody Booking booking){
		log.info(booking+"");
		bookingService.saveBooking(booking);
	}

	@GetMapping("/all")
	public List<Booking> getBookings(@RequestParam(required = false) Long id , String roomType , Integer month, Integer year){

		if (id != null) {
			return bookingService.findById(id);
		}else if(roomType != null){
			return bookingService.findByCategory(roomType);
		} else if (month != null || year != null) {
			return bookingService.findByCheckInDate(
					year != null ? year : LocalDate.now().getYear(),
					month != null ? month : 1
			);
		}

		return bookingService.getAllBookings();
	}

	@PutMapping("/update")
	public void updateBooking(@RequestBody Booking updateBooking){
		bookingService.updateBooking(updateBooking);
	}

	@DeleteMapping("/delete-by-id/{bookingId}")
	public void deleteById(@PathVariable Long bookingId){
		log.info("bookingId1 "+bookingId);
		bookingService.deleteById(bookingId);
	}

}
