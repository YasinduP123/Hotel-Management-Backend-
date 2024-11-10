package lk.icet.hotel.dto;

import lk.icet.hotel.util.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room{
	private Long roomId;
	private Integer roomNumber;
	private RoomType roomType;
	private Double pricePerHour;
	private Double pricePerDay;
}
