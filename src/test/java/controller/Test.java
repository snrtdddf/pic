package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.ibatis.io.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;



public class Test {

	//@org.junit.Test
//	public void buffimg(){
//		try {
//			BufferedImage bufferedImage = 
//					ImageIO.read(new File("D:/mm131/sexyPic/22220172949/1.jpg"));
//			System.out.println(bufferedImage.getWidth()+"::"+bufferedImage.getHeight());
//			bufferedImage = null;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@org.junit.Test
	public void tes(){
		String name = "2222017970";
		if (name.startsWith("222")) {
			String[] temp = name.split("2017");
			String num = temp[1];
			System.out.println(num);
		}else if(name.startsWith("111")){
			String year = name.substring(3, 7);
			String num = name.substring(7,name.length());
			System.out.println(year+num);
		}
	}
}
