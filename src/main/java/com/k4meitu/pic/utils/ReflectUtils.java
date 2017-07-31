package com.k4meitu.pic.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 反射工具类
 * 
 * @author G2326
 *
 */
public class ReflectUtils
{
	static final String getName = "get";
	static final String setName = "set";

	/**
	 * 返回大写字符
	 * 
	 * @param c
	 * @return
	 */
	public static char toUpper(char c)
	{
		return (char) ((c | 0x20) - 0x20);
	}

	/**
	 * 返回小写字符
	 * 
	 * @param c
	 * @return
	 */
	public static char toLower(char c)
	{
		return (char) (c | 0x20);
	}

	/**
	 * 仅对第一个字母大写化
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getPascalName(String fieldName)
	{
		return toUpper(fieldName.charAt(0)) + fieldName.substring(1);
	}

	/**
	 * 仅对第一个字母小写化
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getCamelName(String fieldName)
	{
		return toLower(fieldName.charAt(0)) + fieldName.substring(1);
	}

	/**
	 * 获取 设置方法名
	 * 
	 * @param fieldName 字段名
	 * @return
	 */
	public static String getSetMethodName(String fieldName)
	{
		return setName + getPascalName(fieldName);
	}

	/**
	 * 获取 获取方法名
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getGetMethodName(String fieldName)
	{
		return getName + getPascalName(fieldName);
	}

	/**
	 * 获取 实例字段值
	 * 
	 * @param o 实例对象
	 * @param fieldName [子对象.][字段名]
	 * @return
	 */
	public static Object getInstanceValue(Object o, String fieldName)
	{
		if (o == null || fieldName == null) return null;

		Object value = null;
		String childFiledName = null;

		int li = fieldName.indexOf('.');
		if (li > 0)
		{
			childFiledName = fieldName.substring(li + 1);
			fieldName = fieldName.substring(0, li);
		}

		try
		{
			Method method = o.getClass().getMethod(getGetMethodName(fieldName));
			value = method.invoke(o);
			if (childFiledName != null) return getInstanceValue(value, childFiledName);
		}
		catch (Exception ex)
		{
		}

		return value;
	}

	/**
	 * 设置 实例字段值
	 * 
	 * @param c
	 * @param o
	 * @param fieldName
	 * @param paramType
	 * @param value
	 */
	public static boolean setInstanceValue(Object o, String fieldName, Class<?> paramType, Object value)
	{
		try
		{
			Method method = o.getClass().getMethod(getSetMethodName(fieldName), paramType);

			if (paramType != value.getClass())
			{
				if (paramType == BigDecimal.class)
				{
					value = new BigDecimal(value.toString());
				}
				else if (paramType == Date.class)
				{
					value = DateUtil.parse(value.toString());
				}
				else
				{
					Method setValueMethod = paramType.getMethod("valueOf", String.class);
					value = setValueMethod.invoke(paramType, value);
				}
			}

			method.invoke(o, value);
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}
	/**
	 * 过时方法 下个版本删除
	 * @param c
	 * @param map
	 * @param istrue 过时参数, 无效
	 * @return
	 */
	@Deprecated
	public static <T> T getModelByMap(Class<T> c, Map<String, Object> map, boolean istrue)
	{
		return getModelByMap(c, map);
	}

	/**
	 * 实例化对象 并查找 map 中的数据设置对象值
	 * 
	 * @param c
	 * @param map
	 * @return
	 */
	public static <T> T getModelByMap(Class<T> c, Map<String, Object> map)
	{

		T t = null;

		try
		{
			t = c.newInstance();
		}
		catch (Exception ex)
		{
		}

		if (t != null && map != null)
		{
			Object value = null;
			String fieldName = null;

			for (Field f : getFiledsByModel(c))
			{
				fieldName = f.getName();
				value = map.get(fieldName);
				if (value == null) continue;
				if (!ReflectUtils.setInstanceValue(t, fieldName, f.getType(), value))
				{
					ReflectUtils.setInstanceValue(t, fieldName, f.getType(), value);
				}
			}
		}

		return t;
	}

	/**
	 * 实例化对象 并查找 HttpServletRequest.getParameterMap() 中的数据设置对象值
	 * 无法设置数组对象,获取数组中的最后一个值
	 * 
	 * @param c
	 * @param map
	 * @return
	 */
	public static <T> T getModelByMapServlet(Class<T> c, @SuppressWarnings("rawtypes") Map map)
	{
		return getModelByMap(c, getMapByServletMap(map, null));
	}
	/**
	 * 由 Map<String,String[]> 转换为 Map<String,Object> 只获取数组中的最后一个值
	 * @param map HttpServletRequest.getParameterMap()
	 * @param mapNew 如果为空则为自动生成 HashMap
	 * @param characterEncode 转换编码
	 * @return
	 */
	public static Map<String,Object> getMapByServletMap(@SuppressWarnings("rawtypes") Map map, Map<String, Object> mapNew,String characterEncode)
	{
		if (mapNew == null) mapNew = new HashMap<String, Object>();

		if (map != null && map.size() > 0)
		{
			Object value = null;
			String[] arr = null;
			for (Object key : map.keySet())
			{
				value = map.get(key);
				if (value == null || !(value instanceof String[])) continue;
				arr = (String[]) map.get(key);
				if(characterEncode != null){
					try {
						arr[arr.length - 1] = URLDecoder.decode(arr[arr.length - 1], characterEncode);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						return null;
					}
				}
				mapNew.put(key.toString(), arr[arr.length - 1]);
			}
		}

		return mapNew;
	}
		
	/**
	 * 由 Map<String,String[]> 转换为 Map<String,Object> 只获取数组中的最后一个值
	 * @param map HttpServletRequest.getParameterMap()
	 * @param mapNew 如果为空则为自动生成 HashMap
	 * @return
	 */
	public static Map<String,Object> getMapByServletMap(@SuppressWarnings("rawtypes") Map map, Map<String, Object> mapNew)
	{

		return getMapByServletMap(map,mapNew,null);
	}

	/**
	 * 实例化对象 并查找 HttpServletRequest.getParameterMap() 中的数据设置对象（包括父类对象）值
	 * 无法设置数组对象,获取数组中的最后一个值
	 * 
	 * @param c
	 * @param map
	 * @return
	 */
	public static <T> T getModelByMapServletNew(Class<T> c, @SuppressWarnings("rawtypes") Map map)
	{
		return getModelByMap(c, getMapByServletMap(map, null));
	}

	/**
	 * 依据对象转换为字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String getStringByObject(Object value)
	{
		String ret = "";

		if (value != null)
		{
			if (value instanceof String[])
			{
				String[] values = (String[]) value;
				if (values != null && values.length > 0)
				{
					ret = "[";
					for (int i = 0; i < values.length; i++)
					{
						ret += values[i] + ",";
					}
					ret = ret.substring(0, ret.length() - 1) + "]";
				}
			}
			else
			{
				ret = value.toString();
			}
		}

		return ret;
	}

	/**
	 * 依据字符串转换为对象
	 * 
	 * @param value 参考
	 * org.apache.commons.lang3.builder.ReflectionToStringBuilder
	 * .toString(this);
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectByString(String value)
	{
		int ci, bi, ei, ti;
		if (value == null || (ci = value.indexOf('@')) == -1 || (bi = value.indexOf('[')) == -1 || (ei = value.indexOf(']')) == -1 || ei - bi < 3) { return null; }

		Class<T> c = null;

		try
		{
			c = (Class<T>) Class.forName(value.substring(0, ci));
		}
		catch (Exception ex)
		{
		}

		Map<String, Object> map = new HashMap<String, Object>();

		String[] mapString = value.substring(bi + 1, ei).split(",");

		for (String val : mapString)
		{
			ti = val.indexOf('=');
			map.put(val.substring(0, ti), val.substring(ti + 1));
		}

		return getModelByMap(c, map);
	}
	
	/**
	 * 依据对象转换为 Map
	 * @param t
	 * @return
	 */
	public static <T> Map<String, Object> getMapByModel(T t)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		getMapByModel(t, map);
		return map;
	}
	/**
	 * 依据对象设置 Map
	 * @param t
	 * @param map
	 */
	public static <T> void getMapByModel(T t, Map<String, Object> map)
	{
		if (t != null && map != null)
		{
			Object value = null;
			String fieldName = null;
			
			for (Field f : getFiledsByModel(t.getClass()))
			{
				fieldName = f.getName();
				value = getInstanceValue(t, fieldName);
				if (value == null) continue;
				// 如果有子类继续设置
				if (f.getType().getClassLoader() != null && !f.getType().isEnum()) 
				{
					getMapByModel(value, map);
					continue;
				}
				map.put(fieldName, value);
			}
		}
	}
	/**
	 * 转换对象为另一对象
	 * @param c
	 * @param t
	 */
	public static <T> T getModelByModel(Class<T> c, Object t)
	{
		return getModelByMap(c, getMapByModel(t));
	}
	/**
	 * 获取该类所有字段(包括父类字段)
	 * @param cls
	 * @return
	 */
	public static Field[] getFiledsByModel(Class<?> cls)
	{
		List<Field> list = new ArrayList<Field>();
		
		// 循环获取父类属性
		Class<?> superClass = cls;
		while (superClass.getClassLoader() != null && !superClass.isEnum()) 
		{
			Collections.addAll(list, superClass.getDeclaredFields());
			superClass = superClass.getSuperclass();
		}
		
		return list.toArray(new Field[list.size()]);
	}
	
	/**
	 * 获取xml请求消息对象
	 * @param is HttpServletRequest.getInputStream() 对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapByInputStream(InputStream is, Map<String, Object> map)
	{
		SAXReader sax = new SAXReader();
		Document document = null;
		Element root = null;
		if (map == null) map = new HashMap<String, Object>();

		try
		{
			document = sax.read(is);
			root = document.getRootElement();
		}
		catch (Exception e)
		{
		}
		if (root != null)
		{
			Iterator<Element> it = root.elementIterator(), itChild;
			Element node, nodeChild;

			while (it.hasNext())
			{
				node = it.next();
				itChild = node.elementIterator();
				if (itChild.hasNext())
				{
					do
					{
						nodeChild = itChild.next();
						map.put(getCamelName(nodeChild.getName()), nodeChild.getTextTrim());
					} while (it.hasNext());
				}
				else
				{
					map.put(getCamelName(node.getName()), node.getTextTrim());
				}
			}
		}
		
		return map;
	}

}
