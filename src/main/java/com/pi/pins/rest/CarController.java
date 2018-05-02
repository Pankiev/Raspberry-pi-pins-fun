package com.pi.pins.rest;

import com.pi.pins.service.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController
{
	private final Car car;

	@Autowired
	public CarController(Car car)
	{
		this.car = car;
	}

	@GetMapping("/forward")
	public String moveCarForward()
	{
		car.moveForward();
		return "Ok";
	}

	@GetMapping("/backward")
	public String moveCarBackward()
	{
		car.moveBackward();
		return "Ok";
	}

	@GetMapping("/loose")
	public String looseCar()
	{
		car.loose();
		return "Ok";
	}

	@GetMapping("/turnLeft")
	public String turnCarLeft()
	{
		car.turnLeft();
		return "Ok";
	}

	@GetMapping("/turnRight")
	public String turnCarRight()
	{
		car.turnRight();
		return "Ok";
	}
}
