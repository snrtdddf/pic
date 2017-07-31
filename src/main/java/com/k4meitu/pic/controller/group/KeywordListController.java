package com.k4meitu.pic.controller.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.KeywordListModel;
import com.k4meitu.pic.service.CatalogCoverService;

public class KeywordListController {
	@Autowired
	private CatalogCoverService catalogCoverService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		
		if (param.get("orderByCount").equals("YES")) {
			//关键字搜索次数排行 
			return getKeywordListBySearchCount(param, catalogCoverService);
		}else if(param.get("orderByCount").equals("NO")){
			return getKeywordList(param, catalogCoverService);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ApiConstant.ErrorMsg, "缺少参数或参数不正确");
			return map;
		}
	}
	
	public static Map<String, Object> getKeywordList(
			Map<String, String> param,
			CatalogCoverService catalogCoverService){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String curPageStr = param.get("curPage");
		String pCountStr = param.get("pCount"); 
		int totalPage = 0;
		
		if (StringUtils.isNumeric(curPageStr) && StringUtils.isNumeric(pCountStr)) {
			int curPage = Integer.parseInt(curPageStr);
			int pCount = Integer.parseInt(pCountStr);
			try {
					totalPage = catalogCoverService.getKeywordListCount();
					
					if (( pCount>0 && pCount<=50 ) && 
							curPage >= 0 &&
							totalPage%pCount==0?curPage<totalPage/pCount:curPage<=totalPage/pCount) {
						
						List<KeywordListModel> list = catalogCoverService.getKeywordList(curPage,pCount); 
						
						map.put(ApiConstant.MaxPage, totalPage/pCount);
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
	
	public static Map<String, Object> getKeywordListBySearchCount(
			Map<String, String> param,
			CatalogCoverService catalogCoverService){
		Map<String, Object> map = new HashMap<String, Object>();
	
		try {
			List<KeywordListModel> list = catalogCoverService.getKeywordListBySearchCount();
			map.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
