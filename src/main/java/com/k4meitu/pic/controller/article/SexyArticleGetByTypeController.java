package com.k4meitu.pic.controller.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.SexyArticleModel;
import com.k4meitu.pic.service.ArticleService;

public class SexyArticleGetByTypeController {
	@Autowired
	private ArticleService articleService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		String curPageStr = param.get("curPage");
		String pCountStr = param.get("pCount"); 
		String type = param.get("type");
		String subType = param.get("subType");
		int totalPage = 0;
		
		if (StringUtils.isNumeric(curPageStr) && StringUtils.isNumeric(pCountStr)) {
			int curPage = Integer.parseInt(curPageStr);
			int pCount = Integer.parseInt(pCountStr);
			try {
					totalPage = articleService.getAllLatestArticlesByType(type, subType);
					
					if (( pCount>0 && pCount<=50 ) && 
							curPage >= 0 &&
							totalPage%pCount==0?curPage<totalPage/pCount:curPage<=totalPage/pCount) {
						
						List<SexyArticleModel> list = articleService.getLatestArticlesByType(type, subType, curPage*pCount,pCount); 
						if (list.size() != 0) {
							for(int k=0; k<list.size(); k++){
								SexyArticleModel model = list.get(k);
								
							}
						}
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
