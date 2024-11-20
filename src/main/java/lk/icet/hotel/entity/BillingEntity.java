package lk.icet.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Billing")
public class BillingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billingId;
	private LocalDate date;
	private String amount;
	private String paymentStatus;

//	@OneToOne
//	@JoinColumn(name = "booking_id")
	private Long bookingId;

	public BillingEntity(LocalDate date, String amount, String paymentStatus, Long bookingId) {
		this.date = date;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.bookingId = bookingId;
	}

}
