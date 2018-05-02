package com.pi.pins.service.car;

import com.pi.pins.service.engine.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class Car
{
	private final Engine upperLeftEngine;
	private final Engine middleLeftEngine;
	private final Engine bottomLeftEngine;
	private final Engine upperRightEngine;
	private final Engine middleRightEngine;
	private final Engine bottomRightEngine;
	private final Stream<Engine> allEngines;
	private final Stream<Engine> leftEngines;
	private final Stream<Engine> rightEngines;

	@Autowired
	public Car(@Qualifier("upperLeftEngine") Engine upperLeftEngine,
			@Qualifier("middleLeftEngine") Engine middleLeftEngine,
			@Qualifier("bottomLeftEngine") Engine bottomLeftEngine,
			@Qualifier("upperRightEngine") Engine upperRightEngine,
			@Qualifier("middleRightEngine") Engine middleRightEngine,
			@Qualifier("bottomRightEngine") Engine bottomRightEngine)
	{
		this.upperLeftEngine = upperLeftEngine;
		this.middleLeftEngine = middleLeftEngine;
		this.bottomLeftEngine = bottomLeftEngine;
		this.upperRightEngine = upperRightEngine;
		this.middleRightEngine = middleRightEngine;
		this.bottomRightEngine = bottomRightEngine;
		leftEngines = Stream.of(upperLeftEngine, middleLeftEngine, bottomLeftEngine);
		rightEngines = Stream.of(upperRightEngine, upperRightEngine, upperRightEngine);
		allEngines = Stream.concat(leftEngines, rightEngines);
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
