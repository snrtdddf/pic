package com.k4meitu.pic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.AdvertiseModel;

public interface AdvertisementService {
	public List<AdvertiseModel> getAdvertisement(@Param("type")String type) throws Exception;
}
