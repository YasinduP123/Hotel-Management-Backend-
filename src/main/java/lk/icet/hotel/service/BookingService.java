package lk.icet.hotel.service;


import lk.icet.hotel.dto.Booking;

import java.util.List;

public interface BookingService {

	void saveBooking(Booking booking);

	List<Booking> getAllBookings();

	void updateBooking(Booking booking);

	List<Booking> findById(Long id);

	void deleteById(Long bookingId);

	List<Booking> findByCategory(String roomType);

	List<Booking> findByCheckInDate(Integer year, Integer month);
}
