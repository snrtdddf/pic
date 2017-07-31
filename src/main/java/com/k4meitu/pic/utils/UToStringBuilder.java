package com.k4meitu.pic.utils;
import java.lang.reflect.Field;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 对象为空或变量名已下划线'_'开头不输出
 * @author G2326
 *
 */
public class UToStringBuilder extends ReflectionToStringBuilder
{
	public UToStringBuilder(Object object)
	{
		super(object);
	}
	
	@Override
	protected boolean accept(Field field)
	{
		try
		{
			return super.accept(field) && !field.getName().startsWith("_") && super.getValue(field) != null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 对象转字符串
	 * @param object
	 * @return
	 */
	public static String toString(Object object)
	{
		return new UToStringBuilder(object).build();
	}

}
