package lk.icet.hotel.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StoreOtp {

	private String otp;
	private String email;

}
