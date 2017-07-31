package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.CommentModel;
import com.k4meitu.pic.service.CommentService;
import com.k4meitu.pic.utils.DateUtil;



public class AddCommentController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String groupId = param.get("groupId");
		String imgIdStr = param.get("imgId");
		String userId = param.get("userId");
		String imgComment = param.get("imgComment");
		
		
		if (!(groupId== null || imgIdStr== null || userId == null || imgComment == null) 
				&&(StringUtils.isNumeric(imgIdStr))) {
			CommentModel model = new CommentModel();
			
			model.setDate(DateUtil.getNowTimeStamp());
			model.setGroupId(groupId);
			model.setImgComment(imgComment);
			model.setImgId(Integer.valueOf(imgIdStr));
			model.setUserId(userId);
			model.setIsCommentShow(1);
			
			try {
				 commentService.addComment(model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (model.getId() == 0) {
				map.put(ApiConstant.ErrorMsg, "评论失败");
			}else {
				map.put(ApiConstant.AlertMsg, "comment_success");
			}			
		}else{
			map.put(ApiConstant.ErrorMsg, "参数错误");
		}
		
		
		return map;
	}
}
