package lk.icet.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	private Long bookingId;
	private LocalDate bookingDate;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Customer customer;

}
