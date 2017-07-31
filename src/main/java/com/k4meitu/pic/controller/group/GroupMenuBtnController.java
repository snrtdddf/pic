package com.k4meitu.pic.controller.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.po.MenuBtnModel;
import com.k4meitu.pic.service.CatalogCoverService;

public class GroupMenuBtnController {

	@Autowired
	private CatalogCoverService catalogCoverService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<MenuBtnModel> list = catalogCoverService.getGroupMenuBtn();
			map.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
}
