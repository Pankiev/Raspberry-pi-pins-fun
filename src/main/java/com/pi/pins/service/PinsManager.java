package com.pi.pins.service;

import com.pi4j.io.gpio.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class PinsManager
{
	private final static Log logger = LogFactory.getLog(PinsManager.class);

	private final GpioController gpioController = GpioFactory.getInstance();
	private final Map<Integer, GpioPinDigitalOutput> pins = new ConcurrentHashMap<>();

	@Async
	void turnHigh(int pinNumber)
	{
		logger.info("Turning pin " + pinNumber + " high");
		Pin pinDefinition = toPinDefinition(pinNumber);
		GpioPinDigitalOutput pin = pins.computeIfAbsent(pinNumber, q -> preparePin(pinDefinition));
		if (!pin.isState(PinState.HIGH))
			pin.high();
	}

	@Async
	void turnLow(int pinNumber)
	{
		logger.info("Turning pin " + pinNumber + " low");
		Pin pinDefinition = toPinDefinition(pinNumber);
		GpioPinDigitalOutput pin = pins.computeIfAbsent(pinNumber, q -> preparePin(pinDefinition));
		if (!pin.isState(PinState.LOW))
			pin.low();

	}

	private Pin toPinDefinition(int pinNumber)
	{
		return RaspiPin.getPinByAddress(pinNumber);
	}

	private GpioPinDigitalOutput preparePin(Pin pinDefinition)
	{
		GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(pinDefinition, PinState.LOW);
		pin.setShutdownOptions(false, PinState.LOW, PinPullResistance.PULL_UP, PinMode.DIGITAL_OUTPUT);
		return pin;
	}

	@PreDestroy
	private void shutdownPins()
	{
		logger.info("Shutting down pins...");
		gpioController.shutdown();
	}
}
