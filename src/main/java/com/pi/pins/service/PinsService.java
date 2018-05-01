package com.pi.pins.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PinsService
{
	private final Timer timer = new Timer();
	private final PinsManager pinsManager;

	@Autowired
	public PinsService(PinsManager pinsManager)
	{
		this.pinsManager = pinsManager;
	}

	public void turnHigh(int pinNumber)
	{
		pinsManager.turnHigh(pinNumber);
		timer.schedule(createTimerTask(() -> pinsManager.turnLow(pinNumber)), 1000);
	}

	private TimerTask createTimerTask(Runnable runnable)
	{
		return new TimerTask()
		{
			@Override
			public void run()
			{
				runnable.run();
			}
		};
	}

}
