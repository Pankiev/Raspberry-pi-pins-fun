package com.pi.pins.rest;

import com.pi.pins.rest.exceptions.BadRequestException;
import com.pi.pins.service.pin.PinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pins")
public class PinsController
{
	private final PinsService pinsService;

	@Autowired
	public PinsController(PinsService pinsService)
	{
		this.pinsService = pinsService;
	}

	@GetMapping("/{pinNumber}/activate")
	public String activate(@PathVariable("pinNumber") int pinNumber)
	{
		if (!validPinNumber(pinNumber))
			throw new BadRequestException("Invalid pin");

		pinsService.turnHigh(pinNumber);
		return "Ok";
	}

	@GetMapping("/{pinNumber}/deactivate")
	public String deactivate(@PathVariable("pinNumber") int pinNumber)
	{
		if (!validPinNumber(pinNumber))
			throw new BadRequestException("Invalid pin");

		pinsService.turnLow(pinNumber);
		return "Ok";
	}

	private boolean validPinNumber(int pinNumber)
	{
		return pinNumber >= 0 && pinNumber <= 27;
	}
}
