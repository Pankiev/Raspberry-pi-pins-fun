package com.pi.pins.service.pin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PinsService
{
	private final Timer timer = new Timer();
	private final PinsManager pinsManager;
	private final Map<Integer, TimerTask> highPinsTimerTasks = new ConcurrentHashMap<>();

	@Autowired
	public PinsService(PinsManager pinsManager)
	{
		this.pinsManager = pinsManager;
	}

	public void turnHigh(int pinNumber)
	{
		TimerTask oldTimerTask = highPinsTimerTasks.remove(pinNumber);
		if(oldTimerTask != null)
			oldTimerTask.cancel();

		TimerTask timerTask = createTurnLowTimerTask(pinNumber);
		highPinsTimerTasks.put(pinNumber, timerTask);
		pinsManager.turnHigh(pinNumber);
		timer.schedule(timerTask, 1000);
	}

	public void turnLow(int pinNumber)
	{
		pinsManager.turnLow(pinNumber);
		TimerTask oldTimerTask = highPinsTimerTasks.remove(pinNumber);
		if(oldTimerTask != null)
			oldTimerTask.cancel();
	}

	private TimerTask createTurnLowTimerTask(int pinNumber)
	{
		Runnable runnable = () -> pinsManager.turnLow(pinNumber);
		return new TimerTask()
		{
			@Override
			public void run()
			{
				runnable.run();
				highPinsTimerTasks.remove(pinNumber);
			}
		};
	}

}
