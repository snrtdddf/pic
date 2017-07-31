package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.service.CommentService;

public class AddCommentLikeController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int commentLike = Integer.valueOf(param.get("commentLike"));
		int commentDislike = Integer.valueOf(param.get("commentDislike"));
		int id = Integer.valueOf(param.get("id"));
		
		
		if (commentLike+commentDislike == 1) {
			try {
				int res = commentService.commentLikeAndDislike(commentLike, commentDislike, id);
				if (res > 0) {
					map.put(ApiConstant.Success, true);
				}else{
					map.put(ApiConstant.Success, false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}else{
			map.put(ApiConstant.ErrorMsg, "参数错误");
		}
		
		
		return map;
	}
}
