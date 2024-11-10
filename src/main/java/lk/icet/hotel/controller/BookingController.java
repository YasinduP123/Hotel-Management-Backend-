package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Booking;
import lk.icet.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

	final BookingService bookingService;

	@PostMapping("/set-booking")
	public void setBooking(@RequestBody Booking booking){
		log.info(booking+"");
		bookingService.saveBooking(booking);
	}

	@GetMapping("/all")
	public List<Booking> getBookings(@RequestParam(required = false) Long id){

		if (id != null) {
			return bookingService.findById(id);
		}
		return bookingService.getAllBookings();
	}

	@PutMapping("/update-booking")
	public void updateBooking(@RequestBody Booking updateBooking){
		bookingService.updateBooking(updateBooking);
	}

	@DeleteMapping("/delete-by-id/{bookingId}")
	public void deleteById(@PathVariable Long bookingId){
		log.info("bookingId1 "+bookingId);
		bookingService.deleteById(bookingId);
	}

}
