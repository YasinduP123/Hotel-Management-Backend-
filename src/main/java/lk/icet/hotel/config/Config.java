package lk.icet.hotel.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class Config {

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	@Bean
	public Random random(){return new Random();}
}
