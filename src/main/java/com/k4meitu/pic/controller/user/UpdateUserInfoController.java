package com.k4meitu.pic.controller.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.UserInfo;
import com.k4meitu.pic.service.UserService;


public class UpdateUserInfoController {
	@Autowired
	private UserService userService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String channel = param.get("channel");
		String userId = param.get("userId");
		
		try {
			if (userId.length() == 32) {
				List<UserInfo> list = userService.getUserInfo(userId);
				if (list.size() != 0) {
					UserInfo preUserInfo = list.get(0);
					int loginTimes = preUserInfo.getLoginTimes();
					System.out.println("preloginTimes ==============="+preUserInfo.getLoginTimes());
					UserInfo userInfo = new UserInfo();
					userInfo.setChannel(channel);
					
					userInfo.setUserId(preUserInfo.userId);
					
					String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());//将时间格式转换成符合Timestamp要求的格式.
					Timestamp timestamp =Timestamp.valueOf(nowTime);//把时间转换
					userInfo.setLastLogin(timestamp);
					userInfo.setLoginTimes(loginTimes);
					userService.updateUserInfo(userInfo);
					
					map.put("loginInfo", userInfo);
				}else{
					map.put(ApiConstant.ErrorMsg, "用户不存在");
				}
			}else{
				map.put(ApiConstant.ErrorMsg, "用户id错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
