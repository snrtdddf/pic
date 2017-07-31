package com.k4meitu.pic.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.UserInfo;
import com.k4meitu.pic.service.UserService;

public class GetUserInfoController {
	@Autowired
	private UserService userService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String userId = param.get("userId");
		if (userId.length() == 32) {
			
			List<UserInfo> list;
			try {
				list = userService.getUserInfo(userId);
				if (list.size() != 0) {
					map.put("userInfo", list.get(0));
				}else{
					map.put(ApiConstant.ErrorMsg, "该用户不存在");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			map.put(ApiConstant.ErrorMsg, "用户id错误");
		}
		
		
		
		return map;
	}
	
}
