package com.pi.pins.service.engine;

import com.pi.pins.service.pin.PinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarEnginesProvider
{
	private final PinsService pinsService;

	@Autowired
	public CarEnginesProvider(PinsService pinsService)
	{
		this.pinsService = pinsService;
	}

	@Bean
	public Engine upperLeftEngine() {
		return new Engine(pinsService, 7, 0);
	}

	@Bean
	public Engine middleLeftEngine() {
		return new Engine(pinsService, 4, 6);
	}

	@Bean
	public Engine bottomLeftEngine() {
		return new Engine(pinsService, 10, 11);
	}

	@Bean
	public Engine upperRightEngine() {
		return new Engine(pinsService, 1, 15);
	}

	@Bean
	public Engine middleRightEngine() {
		return new Engine(pinsService, 3, 13);
	}

	@Bean
	public Engine bottomRightEngine() {
		return new Engine(pinsService, 14, 21);
	}
}