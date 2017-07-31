package com.k4meitu.pic.handler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.constant.ErrorMsgCode;
import com.k4meitu.pic.utils.RegexUtils;
import com.k4meitu.pic.utils.SpringUtils;

public class ApiHandler {
	
	public static Map<String, String> getParameter(Map<String, String[]> map){
		
		Map<String, String> param = new HashMap<String, String>();
		
		if (map == null) {
			param.put(ApiConstant.ErrorMsg, ErrorMsgCode.E0001);//请求参数为空
			return param;
		}else{
			 for (Entry<String, String[]> m :map.entrySet())  { 
				 	//判断key的value是否传入了空值
				 	if (!m.getValue()[0].equals("")) {
				 		 param.put(m.getKey(), m.getValue()[0]);
					}else{
						param.put(ApiConstant.ErrorMsg, ErrorMsgCode.E0003);//请求参数的值为空
					}
		     }  
			 
			 //判断有没有漏掉必传的参数
			 Set<String> keys = param.keySet();
			 if(!(keys.contains(ApiConstant.IP)&&
					 keys.contains(ApiConstant.AppChannel)&&
					 (keys.contains(ApiConstant.Method))))
			 {
				 param.put(ApiConstant.ErrorMsg, ErrorMsgCode.E0002);//缺少请求参数
			 };
			 	
			 
			 
			 return param;
		}
	}
	
	//判断传入的参数是否合法
	public static boolean isParamCorrect(Map<String, String> param){
		String ErrorMsg = param.get(ApiConstant.ErrorMsg);
		if (ErrorMsg == null) {
			if ((ApiHandler.findControllerByApiMethod(param.get(ApiConstant.Method)) != null)&&
					(param.get(ApiConstant.AppChannel).equals(ApiConstant.Android)||
							param.get(ApiConstant.AppChannel).equals(ApiConstant.iOS))&&
					(RegexUtils.isIP(param.get(ApiConstant.IP)))
						) {
					return true;
				}else{
					 param.put(ApiConstant.ErrorMsg, ErrorMsgCode.E0004);//请求参数不正确
					return false;
				}
		}else{
			param.put(ApiConstant.ErrorMsg, ErrorMsg);
			return false;
		}
		/*
		if (ErrorMsg== null&&
			(ApiHandler.findControllerByApiMethod(param.get(ApiConstant.Method)) != null)&&
			(param.get(ApiConstant.AppChannel).equals(ApiConstant.Android)||
					param.get(ApiConstant.AppChannel).equals(ApiConstant.iOS))&&
			(RegexUtils.isIP(param.get(ApiConstant.IP)))
				) {
			return true;
		}else{
			 param.put(ApiConstant.ErrorMsg, ErrorMsgCode.E0004);//请求参数不正确
			return false;
		}
		*/
	}
	
	//通过方法名获得类
	public static String findControllerByApiMethod(String methodName){
		String value = null;
		try {
			InputStream in = Resources.getResourceAsStream("config/method.properties");
			Properties prop = new Properties();
			prop.load(in);
			Enumeration en = prop.propertyNames(); //得到配置文件的名字
			while(en.hasMoreElements()) {
				 String strKey = (String)en.nextElement();
				 if (methodName.equals(strKey)) {
					return  prop.getProperty(strKey);
				}
			}
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> invokeObjectMethod(String className,Map<String, String> paramMap)
	{
		Object obj = null;
		try {
			Object beanClazz = SpringUtils.getBean(className);
			if (beanClazz!=null) {
				Method method =  beanClazz.getClass().getDeclaredMethod("resultMap", Map.class);
				obj = method.invoke(beanClazz, paramMap);
				return (Map<String, Object>)obj;
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Map<String, Object>) obj;
	}
	
	
	
}
