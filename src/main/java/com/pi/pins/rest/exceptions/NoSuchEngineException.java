package com.pi.pins.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchEngineException extends RuntimeException
{
	public NoSuchEngineException(String engineName)
	{
		super(engineName);
	}
}
