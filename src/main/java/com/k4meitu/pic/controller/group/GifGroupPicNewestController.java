package com.k4meitu.pic.controller.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.GifGroupPicNewestModel;

import com.k4meitu.pic.service.CatalogCoverService;

public class GifGroupPicNewestController {
	@Autowired
	CatalogCoverService catalogCoverService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String curPageStr = param.get("curPage");
		String pCountStr = param.get("pCount"); 
		String type = param.get("type");
		int totalPage = 0;
		
		if (StringUtils.isNumeric(curPageStr) && StringUtils.isNumeric(pCountStr) && type != null) {
			int curPage = Integer.parseInt(curPageStr);
			int pCount = Integer.parseInt(pCountStr);
			try {
					totalPage = catalogCoverService.getAllGifGroupPicNewest();
					
					if (( pCount>0 && pCount<=50 ) && 
							curPage >= 0 &&
							totalPage%pCount==0?curPage<totalPage/pCount:curPage<=totalPage/pCount) {
						List<GifGroupPicNewestModel> list = catalogCoverService.getGifGroupPicNewest(type, curPage*pCount,pCount); 
						map.put("maxPage", totalPage/pCount);
						map.put("list", list);
					}else{
						map.put(ApiConstant.ErrorMsg, "page参数范围有误");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{
			map.put(ApiConstant.ErrorMsg, "page参数值类型不正确");
		}
		return map;
	}
	
}
