package com.k4meitu.pic.controller.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.PicGroupModel;
import com.k4meitu.pic.service.CatalogCoverService;


public class GroupController {
	
	@Autowired
	private CatalogCoverService catalogCoverService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();

		String curPageStr = param.get("curPage");
		String pCountStr = param.get("pCount"); 
		int totalPage = 0;
		
		if (StringUtils.isNumeric(curPageStr) && StringUtils.isNumeric(pCountStr)) {
			int curPage = Integer.parseInt(curPageStr);
			int pCount = Integer.parseInt(pCountStr);
			try {
					totalPage = catalogCoverService.getAllCatalogCover();
					
					if (( pCount>0 && pCount<=50 ) && 
							curPage >= 0 &&
							totalPage%pCount==0?curPage<totalPage/pCount:curPage<=totalPage/pCount) {
						
						List<PicGroupModel> list = catalogCoverService.getCatalogCoverListInPage(curPage*pCount,pCount); 
						if (list.size() != 0) {
							for(int k=0; k<list.size(); k++){
								PicGroupModel model = list.get(k);
								model.setImgUrl(ApiConstant.ImgPath + model.getGroupId()+"/"+model.getImgCoverName());
								if (model.getType().equals("xinggan")) {
									model.setType("性感");
								}else if (model.getType().equals("qingchun")) {
									model.setType("清纯");
								}else if (model.getType().equals("chemo")) {
									model.setType("车模");
								}else if (model.getType().equals("xiaohua")) {
									model.setType("校花");
								}else if (model.getType().equals("qipao")) {
									model.setType("旗袍");
								}else if (model.getType().equals("mmjpg_home")) {
									model.setType("美女");
								}else if (model.getType().equals("mingxing")) {
									model.setType("明星");
								}
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
