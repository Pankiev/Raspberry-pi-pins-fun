package com.pi.pins.service.car;

import com.pi.pins.service.engine.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Car
{
	private final Collection<Engine> allEngines;
	private final Collection<Engine> leftEngines;
	private final Collection<Engine> rightEngines;

	@Autowired
	public Car(@Qualifier("upperLeftEngine") Engine upperLeftEngine,
			@Qualifier("middleLeftEngine") Engine middleLeftEngine,
			@Qualifier("bottomLeftEngine") Engine bottomLeftEngine,
			@Qualifier("upperRightEngine") Engine upperRightEngine,
			@Qualifier("middleRightEngine") Engine middleRightEngine,
			@Qualifier("bottomRightEngine") Engine bottomRightEngine)
	{
		leftEngines = Collections.unmodifiableCollection(Stream.of(upperLeftEngine, middleLeftEngine, bottomLeftEngine)
				.collect(Collectors.toList()));
		rightEngines = Collections.unmodifiableCollection(Stream.of(upperRightEngine, middleRightEngine, bottomRightEngine)
				.collect(Collectors.toList()));
		allEngines = Collections.unmodifiableCollection(Stream.concat(leftEngines.stream(), rightEngines.stream())
				.collect(Collectors.toList()));
	}

	public void moveForward()
	{
		allEngines.forEach(Engine::startForward);
	}

	public void moveBackward()
	{
		allEngines.forEach(Engine::startBackward);
	}

	public void loose()
	{
		allEngines.forEach(Engine::loose);
	}

	public void turnLeft()
	{
		leftEngines.forEach(Engine::startBackward);
		rightEngines.forEach(Engine::startForward);
	}

	public void turnRight()
	{
		leftEngines.forEach(Engine::startForward);
		rightEngines.forEach(Engine::startBackward);
	}
}
