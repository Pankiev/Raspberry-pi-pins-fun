package com.pi.pins.service.pin;

import com.pi.pins.SpringBootStarter;
import com.pi.pins.configuration.AsyncConfiguration;
import com.pi.pins.rest.CarController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.Consumer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringBootStarter.class, CarTestConfiguration.class, AsyncConfiguration.class})
@ActiveProfiles("test")
class SomeTest
{
	@Autowired
	private CarController car;

	@Test
	void test()
	{
		runAsync(CarController::moveCarForward, 1000, 10);
		runAsync(CarController::moveCarBackward, 1000, 10);
		runAsync(CarController::turnCarLeft, 1000, 10);
		runAsync(CarController::turnCarRight, 1000, 10);
		sleep(20000);
	}

	private void runAsync(Consumer<CarController> action, int times, long intervalMilis)
	{
		new Thread(() -> {
			for(int i=0; i<times; i++)
			{
				new Thread(() -> action.accept(car)).start();
				sleep(intervalMilis);
			}
		}).start();
	}

	private void sleep(long millis)
	{
		try
		{
			Thread.sleep(millis);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
