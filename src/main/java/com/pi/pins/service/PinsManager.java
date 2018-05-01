package com.pi.pins.service;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class PinsManager
{
	private final static Log logger = LogFactory.getLog(PinsManager.class);

	@Async
	void turnHigh(int pinNumber)
	{
		logger.info("Turning pin " + pinNumber + " high");
		Pin pin = toPin(pinNumber);
	}

	@Async
	void turnLow(int pinNumber)
	{
		logger.info("Turning pin " + pinNumber + " low");
		Pin pin = toPin(pinNumber);
	}

	private Pin toPin(int pinNumber)
	{
		return RaspiPin.getPinByAddress(pinNumber);
	}
}
