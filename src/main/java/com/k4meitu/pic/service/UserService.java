package com.k4meitu.pic.service;

import java.util.List;

import com.k4meitu.pic.po.UserInfo;

public interface UserService {
	//用户注册
		public void registerUser(UserInfo userInfo) throws Exception;
		
		//获取用户信息
		public List<UserInfo> getUserInfo(String userId) throws Exception;
		
		//更新用户信息
		public void updateUserInfo(UserInfo userInfo) throws Exception;
}
