package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.k4meitu.pic.service.CommentService;

public class MostLikePicGroupController {

	@Autowired
	private CommentService commentService;
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = commentService.findMostLikeGroup();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
}
