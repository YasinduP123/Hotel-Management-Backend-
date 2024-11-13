package lk.icet.hotel.service.impl;

import lk.icet.hotel.dto.Booking;
import lk.icet.hotel.entity.BookingEntity;
import lk.icet.hotel.repository.BookingRepository;
import lk.icet.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	final ModelMapper mapper;
	final BookingRepository bookingRepository;

	@Override
	
	public void saveBooking(Booking booking) {
		BookingEntity map = mapper.map(booking, BookingEntity.class);
		log.info("booking "+map);
		bookingRepository.save(map);
	}

	@Override
	public List<Booking> getAllBookings() {
		List<Booking> bookingList = new ArrayList<>();
		bookingRepository.findAll().forEach(booking -> bookingList.add(mapper.map(booking, Booking.class)));
		return bookingList;
	}

	@Override
	public void updateBooking(Booking booking){
		bookingRepository.save(mapper.map(booking,BookingEntity.class));
	}

	@Override
	public List<Booking> findById(Long id) {
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(mapper.map(bookingRepository.findById(id), Booking.class));
		return bookingList;
	}

	@Override
	public void deleteById(Long bookingId) {
		log.info("bookingId "+bookingId);
		bookingRepository.deleteById(bookingId);

	}

	@Override
	public List<Booking> findByCategory(String roomType) {
		List<Booking> bookingList = new ArrayList<>();
		bookingRepository.findByRoomType(roomType).forEach(bookingEntity -> bookingList.add(mapper.map(bookingEntity, Booking.class)));
		return bookingList;
	}


}