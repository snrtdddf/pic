package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.service.CommentService;
import com.k4meitu.pic.utils.DateUtil;

public class AddLikeController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String groupId = param.get("groupId");
		String userId = param.get("userId");
		String imgLike = param.get("imgLike");
		String imgDislike = param.get("imgDislike");
		String imgId = param.get("imgId");
		if (!(groupId== null 
			|| userId == null 
			|| imgLike == null 
			|| imgId == null 
			|| imgDislike == null) 
			&&(StringUtils.isNumeric(imgLike))
			&&(StringUtils.isNumeric(imgDislike))) {
			if (!(Integer.valueOf(imgDislike)+Integer.valueOf(imgLike)>1)) {
				
				LikeModel model = new LikeModel();
				
				model.setGroupId(groupId);
				model.setImgDislike(Integer.valueOf(imgDislike));
				model.setImgLike(Integer.valueOf(imgLike));
				model.setUserId(userId);
				model.setImgId(Integer.valueOf(imgId));
				model.setDate(DateUtil.getNowTimeStamp());
				
				try {
					List<LikeModel> list = commentService.findLikeIsExist(model);
					if (list.size() == 0) {
						commentService.addLike(model);
						if (model.getId() != 0) {
							map.put(ApiConstant.AlertMsg, "like_success");
						}
					}else{
						map.put(ApiConstant.ErrorMsg, "已经点过赞了");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				map.put(ApiConstant.ErrorMsg, "点赞失败");
			}
		}else{
			map.put(ApiConstant.ErrorMsg, "传入参数类型错误");
		}
		return map;
	}
}
