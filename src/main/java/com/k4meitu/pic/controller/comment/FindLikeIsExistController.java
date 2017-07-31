package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.service.CommentService;


public class FindLikeIsExistController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String groupId = param.get("groupId");
		String userId = param.get("userId");
		
		
		if (!(groupId== null  || userId == null )) {
			LikeModel model = new LikeModel();
			model.setGroupId(groupId);
			model.setUserId(userId);
			
			try {
				List<LikeModel> list = commentService.findLikeIsExist(model);
				if (list.size() != 0) {
					map.put("isLiked", true);
				}else{
					map.put("isLiked", false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			map.put(ApiConstant.ErrorMsg, "传入参数类型错误");
		}
		return map;
	}
}
