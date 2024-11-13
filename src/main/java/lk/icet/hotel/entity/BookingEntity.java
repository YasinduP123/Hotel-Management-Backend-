package lk.icet.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Booking")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	private LocalDate bookingDate;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String roomType;

	@ManyToOne
	@JoinColumn(name = "id")
	private CustomerEntity customer;

	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private BillingEntity billing;

}
