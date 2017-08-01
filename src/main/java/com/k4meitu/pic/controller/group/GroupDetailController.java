package com.k4meitu.pic.controller.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.ImgPropModel;
import com.k4meitu.pic.service.CatalogCoverService;

public class GroupDetailController {
	@Autowired
	private CatalogCoverService catalogCoverService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		String groupId = param.get("groupId");
		if (groupId != null) {
			try {
				List<ImgPropModel> list = catalogCoverService.findPicDetailByGroupId(groupId);
				if (list.size() != 0) {
					for(int k=0; k<list.size(); k++){
						ImgPropModel model = list.get(k);
//						if (model.getGroupId().startsWith("222")) {
//							String[] temp = model.getGroupId().split("2017");
//							String num = temp[1];
//							model.setImgUrl("http://img1.mm131.com/pic/"+num+"/"+(k+1)+".jpg");
//						}else if(model.getGroupId().startsWith("111")){
//							String year = model.getGroupId().substring(3, 7);
//							String num = model.getGroupId().substring(7,model.getGroupId().length());
//							System.out.println(year+num);
//							model.setImgUrl("http://img.mmjpg.com/"+year+"/"+num+"/"+(k+1)+".jpg");
//						}
						model.setImgUrl(ApiConstant.ImgPath + model.getGroupId()+"/"+model.getImgName());
					}
				}
				map.put("list", list); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			map.put(ApiConstant.ErrorMsg, "图组ID不能为空");
		}
		
		return map;
	}
}
