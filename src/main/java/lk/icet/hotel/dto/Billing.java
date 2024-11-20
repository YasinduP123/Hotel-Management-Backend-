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
	private Long bookingId;

	public Billing(LocalDate date, String amount, String paymentStatus, Long bookingId) {
		this.date = date;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.bookingId = bookingId;
	}

}
