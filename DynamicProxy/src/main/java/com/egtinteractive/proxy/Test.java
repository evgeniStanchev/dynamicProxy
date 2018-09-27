package com.egtinteractive.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class Test {
    public static void main(String[] args) {
	final Class<Test> testClass = Test.class;
	final ClassLoader classLoader = testClass.getClassLoader();
	final Class<?>[] interfaces = new Class[] { Map.class };
	final Map<String, Integer> map = new HashMap<>();
	final CustomHandler dynamicProxy = new CustomHandler(map);

	@SuppressWarnings("unchecked")
	final Map<String, Integer> proxy = (HashMap<String, Integer>) Proxy.newProxyInstance(classLoader, interfaces,
		dynamicProxy);

	final String str1 = UUID.randomUUID().toString();
	final String str2 = UUID.randomUUID().toString();
	final String str3 = UUID.randomUUID().toString();
	final int num1 = ThreadLocalRandom.current().nextInt();
	final int num2 = ThreadLocalRandom.current().nextInt();
	final int num3 = ThreadLocalRandom.current().nextInt();

	proxy.put(str1, num1);
	proxy.put(str2, num2);
	proxy.put(str3, num3);

	proxy.containsKey(str1);
	proxy.containsKey(UUID.randomUUID().toString());
	proxy.remove(str1);
	proxy.keySet();
	proxy.get(str1);
	proxy.get(UUID.randomUUID().toString());
    }
}
