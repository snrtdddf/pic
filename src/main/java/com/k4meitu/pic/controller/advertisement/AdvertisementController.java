package com.k4meitu.pic.controller.advertisement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.AdvertiseModel;
import com.k4meitu.pic.service.AdvertisementService;

public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String type = param.get("type");
		
		if (type != null) {
			try {
				List<AdvertiseModel> list = advertisementService.getAdvertisement(type);
				map.put("list", list);
			} catch (Exception e) {
				e.printStackTrace();
				map.put(ApiConstant.ErrorMsg, "获取广告失败");
			}
		}else{
			map.put(ApiConstant.ErrorMsg, "type为空");
		}
		return map;
	}
}
