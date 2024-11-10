package lk.icet.hotel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BookingServiceTest.class)
class BookingServiceTest {

	@Autowired
	BookingService bookingService;

	

}
