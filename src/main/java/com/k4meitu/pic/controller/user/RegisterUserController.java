package com.k4meitu.pic.controller.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;


import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.UserInfo;
import com.k4meitu.pic.service.UserService;


public class RegisterUserController {
	@Autowired
	private UserService userService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		String ip = param.get("ip");
		String channel = param.get("channel");
		long timpStamp = new java.util.Date().getTime();
		String userId =  DigestUtils.md5Hex((timpStamp+""+ip).getBytes());
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUserId(userId);
		
		userInfo.setLoginTimes(0);
		
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());//将时间格式转换成符合Timestamp要求的格式.
		Timestamp timestamp =Timestamp.valueOf(nowTime);//把时间转换
		
		userInfo.setLastLogin(timestamp);
		
		userInfo.setChannel(channel);
		
		try {
			userService.registerUser(userInfo);
			List<UserInfo> list = userService.getUserInfo(userId);
			if (list.size() != 0) {
				map.put("userInfo", list.get(0));
			}else{
				map.put(ApiConstant.ErrorMsg, "注册失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
