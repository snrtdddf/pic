package com.k4meitu.pic.controller.comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.service.CommentService;

public class AddGifPicGroupLikeController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int imgLike = Integer.valueOf(param.get("like"));
		int imgDislike = Integer.valueOf(param.get("dislike"));
		int id = Integer.valueOf(param.get("id"));
		String groupId = param.get("groupId");
		String userId = param.get("userId");

		if ((imgLike+imgDislike == 1) && (groupId != null) && (userId != null)) {
			try {
				LikeModel likeModel = new LikeModel();
				likeModel.setGroupId(groupId);
				likeModel.setUserId(userId);
				List<LikeModel> likeModels = commentService.findLikeIsExist(likeModel);
				if (likeModels.size() == 0) {
					int res = commentService.addGifPicGroupLikeAndDislike(imgLike, imgDislike, id);
					if (res > 0) {
						map.put(ApiConstant.Success, true);
					}else{
						map.put(ApiConstant.Success, false);
					}
				}else{
					LikeModel model = likeModels.get(0);
					if (model.getImgLike() == 1) {
						map.put(ApiConstant.ErrorMsg, "已经点过赞了");
					}else if (model.getImgDislike() == 1) {
						map.put(ApiConstant.ErrorMsg, "已经踩过了");
					}
					return map;
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
