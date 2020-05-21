package com.ibm.fsd.my.stock.bkg.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvUtils implements EnvironmentAware {
	private static Environment env;

	public static String getProperty(String key) {
		return env.getProperty(key);
	}

	@Override
	public void setEnvironment(Environment environment) {
		injectEnvironment(env); // 因为spring会创建这个接口的实现类的一个对象，所以实例方法调用静态方法，只是目前这个类我们是看不到的
	}

	public static void injectEnvironment(Environment env) {
		EnvUtils.env = env; // 这其实是实例方法调用静态方法
	}
}
