package lk.icet.hotel.entity;

import jakarta.persistence.*;
import lk.icet.hotel.util.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "room")
public class RoomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	@Column(unique = true)
	private Integer roomNumber;
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	private Double pricePerHour;
	private Double pricePerDay;
}
