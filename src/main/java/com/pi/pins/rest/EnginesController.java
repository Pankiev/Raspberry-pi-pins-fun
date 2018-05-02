package com.pi.pins.rest;

import com.pi.pins.rest.exceptions.NoSuchEngineException;
import com.pi.pins.service.engine.Engine;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engines")
public class EnginesController
{
	private final ApplicationContext applicationContext;

	@Autowired
	public EnginesController(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}

	@GetMapping("/{engineName}/forward")
	public String startEngineForward(@PathVariable("engineName") String engineName)
	{
		Engine engine = getEngine(engineName);
		engine.startForward();
		return "Ok";
	}

	@GetMapping("/{engineName}/backward")
	public String startEngineBackward(@PathVariable("engineName") String engineName)
	{
		Engine engine = getEngine(engineName);
		engine.startBackward();
		return "Ok";
	}

	@GetMapping("/{engineName}/loose")
	public String looseEngine(@PathVariable("engineName") String engineName)
	{
		Engine engine = getEngine(engineName);
		engine.loose();
		return "Ok";
	}

	private Engine getEngine(String engineName)
	{
		try
		{
			return applicationContext.getBean(engineName, Engine.class);
		} catch (NoSuchBeanDefinitionException e)
		{
			throw new NoSuchEngineException(engineName);
		}
	}
}
