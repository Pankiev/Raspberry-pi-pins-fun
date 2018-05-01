package com.pi.pins;

import com.pi4j.io.gpio.*;

public class PinsLauncher
{
	public static void main2(String[] args) throws InterruptedException
	{
		System.out.println("<--Pi4J--> GPIO Control Example ... started.");

		// create gpio service
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.HIGH);

		// set shutdown state for this pi
		pin.setShutdownOptions(false, PinState.LOW, PinPullResistance.PULL_UP, PinMode.DIGITAL_OUTPUT);

		System.out.println("--> GPIO state should be: ON");

		Thread.sleep(5000);

		pin.low();
		System.out.println("--> GPIO state should be: OFF");

		Thread.sleep(5000);

		// turnHigh the current state of gpio pin #01 (should turn on)
		//pin.turnHigh();
		//System.out.println("--> GPIO state should be: ON");

		//Thread.sleep(5000);

		// turnHigh the current state of gpio pin #01  (should turn off)
		//pin.turnHigh();
		//System.out.println("--> GPIO state should be: OFF");

		//Thread.sleep(5000);

		// turn on gpio pin #01 for 1 second and then off
		//System.out.println("--> GPIO state should be: ON for only 1 second");
		//pin.pulse(1000, true); // set second argument to 'true' use a blocking call

		// stop all GPIO activity/threads by shutting down the GPIO service
		// (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
		gpio.shutdown();

		System.out.println("Exiting ControlGpioExample");
	}
}
