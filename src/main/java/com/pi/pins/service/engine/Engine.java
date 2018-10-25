package com.pi.pins.service.engine;

import com.pi.pins.service.pin.PinsService;
import org.springframework.scheduling.annotation.Async;

public class Engine
{
	private final PinsService pinsService;
	private final int leftControlPin;
	private final int rightControlPin;

	Engine(PinsService pinsService, int leftControlPin, int rightControlPin)
	{
		this.pinsService = pinsService;
		this.leftControlPin = leftControlPin;
		this.rightControlPin = rightControlPin;
	}

	@Async
	public synchronized void startForward()
	{
		pinsService.turnLow(leftControlPin);
		pinsService.turnHigh(rightControlPin);
	}

	@Async
	public synchronized void startBackward()
	{
		pinsService.turnLow(rightControlPin);
		pinsService.turnHigh(leftControlPin);
	}

	@Async
	public synchronized void loose()
	{
		pinsService.turnLow(rightControlPin);
		pinsService.turnLow(leftControlPin);
	}
}
