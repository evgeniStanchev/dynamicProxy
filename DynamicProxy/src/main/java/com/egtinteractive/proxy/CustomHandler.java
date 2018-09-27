package com.egtinteractive.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class CustomHandler implements InvocationHandler {

    private final Map<?, ?> map;

    public CustomHandler(final Map<?, ?> map) {
	this.map = map;
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
	method.setAccessible(true);
	final String methodName = method.getName();
	final Date date = new Date();
	final String startsAt = new SimpleDateFormat("HH:mm:ss:SSS").format(date);
	final double startTime = System.nanoTime();
	final Object result = method.invoke(map, args);
	final double endTime = System.nanoTime();
	final double duration = (endTime - startTime) / 1000000;
	System.out.printf("Method:%s" + System.lineSeparator() + "Start:%s" + System.lineSeparator() + "Duration:%f"
		+ System.lineSeparator() + System.lineSeparator(), methodName, startsAt, duration);
	return result;
    }
}