package com.k4meitu.pic.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware{

	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringUtils.context = applicationContext;
	}

	public static Object getBean(String name)
	{
		return context.getBean(name);
	}
	
	/**
	 * 获取  bean 实例
	 * @param name
	 * @return throws BeansException
	 */
	public static <T> T getBean(Class<T> cls)
	{
		return context.getBean(cls);
	}
	
	public static <T> T getBean(String name, Class<T> type)
	{
		if (name == null || name.trim().length() == 0) return null;
		
		T t = null;
		try
		{
			t = context.getBean(name, type);
		}
		catch(Exception ex)
		{
		}
		return t;
	}
}
