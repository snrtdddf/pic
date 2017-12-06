package com.k4meitu.pic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.mapper.AdvertisementMapper;
import com.k4meitu.pic.po.AdvertiseModel;
import com.k4meitu.pic.service.AdvertisementService;

public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	private AdvertisementMapper advertisementMapper;
	
	@Override
	public List<AdvertiseModel> getAdvertisement(String type) throws Exception {
		// TODO Auto-generated method stub
		return advertisementMapper.getAdvertisement(type);
	}

}
