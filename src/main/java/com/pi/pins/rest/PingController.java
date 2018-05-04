package com.pi.pins.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController
{
	@GetMapping("/ping")
	public boolean ping()
	{
		return true;
	}
}
