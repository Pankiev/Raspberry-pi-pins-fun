package com.pi.pins.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler
{
	private final Log logger = LogFactory.getLog(SimpleAsyncUncaughtExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... objects)
	{
		this.logger.error(String.format("Unexpected error occurred invoking async method '%s'.", method), throwable);
	}
}
