package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.CommentModel;
import com.k4meitu.pic.service.CommentService;

public class GetCommentController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String groupId = param.get("groupId");
		String curPageStr = param.get("curPage");
		String pCountStr = param.get("pCount");
		if (StringUtils.isNumeric(curPageStr) && StringUtils.isNumeric(pCountStr)) {
			List<CommentModel> list = null;
			int curPage = Integer.valueOf(curPageStr);
			int pCount = Integer.valueOf(pCountStr);
			int commentCount = 0;
			int likeCount = 0;
			try {
				commentCount = commentService.getCommentCountByGroupId(groupId);
				likeCount = commentService.getPicGroupLikeCount(groupId);
				if (( pCount>0 && pCount<=20 ) && curPage >= 0 ) {
					//&&commentCount%pCount==0?curPage<commentCount/pCount:curPage<=commentCount/pCount
					
					list = commentService.getCommentByGroupId(groupId,curPage*pCount,pCount);
					
					map.put("maxPage", commentCount/pCount);
					map.put("commentCount", commentCount);
					map.put("likeCount", likeCount);
					map.put("list", list);	
					
					return map;
				}else{
					
					map.put(ApiConstant.ErrorMsg, "参数范围错误");
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else{
			map.put(ApiConstant.ErrorMsg, "参数类型错误");
		}
		
		
		return map;
	}
}
