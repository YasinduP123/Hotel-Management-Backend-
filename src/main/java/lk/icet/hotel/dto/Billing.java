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
public class Billing {

	private Long billingId;
	private LocalDate date;
	private String amount;
	private String paymentStatus;
	private Booking booking;

	public Billing(LocalDate date, String amount, String paymentStatus, Booking booking) {
		this.date = date;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.booking = booking;
	}

}
