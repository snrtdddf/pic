package com.k4meitu.pic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.mapper.UserMapper;
import com.k4meitu.pic.po.UserInfo;
import com.k4meitu.pic.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void registerUser(UserInfo userInfo) throws Exception{
		userMapper.registerUser(userInfo);
	}

	@Override
	public List<UserInfo> getUserInfo(String userId) throws Exception{
		// TODO Auto-generated method stub
	
		return userMapper.getUserInfo(userId);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) throws Exception{
		userMapper.updateUserInfo(userInfo);
	}

}
