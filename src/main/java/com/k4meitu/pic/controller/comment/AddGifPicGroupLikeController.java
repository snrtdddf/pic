package com.k4meitu.pic.controller.comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.ImgCmtLikeModel;
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

		int commentLike = imgLike;
		int commentDislike = imgDislike;
		int commentId = id;
		int imgId = Integer.valueOf(param.get("imgId"));
		String likeUserId = param.get("likeUserId");
		int likeOrDislike = commentLike > commentDislike ? 1 : 0;

		ImgCmtLikeModel cmtLikeModel = new ImgCmtLikeModel();
		cmtLikeModel.setCommentId(commentId);
		cmtLikeModel.setGroupId(groupId);
		cmtLikeModel.setImgId(imgId);
		cmtLikeModel.setLikeOrDislike(likeOrDislike);
		cmtLikeModel.setLikeUserId(likeUserId);
		cmtLikeModel.setUserId(userId);
		
		try {
			List<ImgCmtLikeModel> list = commentService.findCommentLikeIsExistByCommentId(commentId, likeUserId);
			if (list.size() != 0) {
				map.put(ApiConstant.ErrorMsg, "已经赞过或踩过啦...");
			}else{
				if (commentLike+commentDislike == 1) {
					try {
						int res1 = commentService.addCommentLikeWithUser(cmtLikeModel);
						if (res1 > 0) {
							int res = commentService.addGifPicGroupLikeAndDislike(imgLike, imgDislike, id);
							if (res > 0) {
								map.put(ApiConstant.Success, true);
							}else{
								map.put(ApiConstant.Success, false);
							}
						}else{
							map.put(ApiConstant.Success, false);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else{
					map.put(ApiConstant.ErrorMsg, "参数错误");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
}
