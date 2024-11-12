package lk.icet.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room{
	private Long roomId;
	private Integer roomNumber;
	private String roomType;
	private Double pricePerHour;
	private Double pricePerDay;
}
