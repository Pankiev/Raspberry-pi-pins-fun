package com.pi.pins.service.engine;

import com.pi.pins.service.pin.PinsService;

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

	public void startForward()
	{
		pinsService.turnLow(leftControlPin);
		pinsService.turnHigh(rightControlPin);
	}

	public void startBackward()
	{
		pinsService.turnLow(rightControlPin);
		pinsService.turnHigh(leftControlPin);
	}

	public void loose()
	{
		pinsService.turnLow(rightControlPin);
		pinsService.turnLow(leftControlPin);
	}
}
