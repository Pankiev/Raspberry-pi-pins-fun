package com.pi.pins.service.pin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CarTestConfiguration
{

	@Bean
	@Primary
	public PinsManager pinsManager()
	{
		return new PinsManager(new GpioControllerMock());
	}
}