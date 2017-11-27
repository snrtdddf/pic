package com.k4meitu.pic.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.po.ImgCmtLikeModel;
import com.k4meitu.pic.service.CommentService;

public class AddCommentLikeController {
	@Autowired
	private CommentService commentService;
	
	
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int commentLike = Integer.valueOf(param.get("commentLike"));
		int commentDislike = Integer.valueOf(param.get("commentDislike"));
		int id = Integer.valueOf(param.get("id"));
		int commentId = id;
		String groupId = param.get("groupId");
		int imgId = Integer.valueOf(param.get("imgId"));
		String likeUserId = param.get("likeUserId");
		int likeOrDislike = commentLike > commentDislike ? 1 : 0;
		String userId = param.get("userId");
		//commentId, groupId, imgId,userId, imgComment, likeUserId, likeOrDislike, date
		
		ImgCmtLikeModel cmtLikeModel = new ImgCmtLikeModel();
		cmtLikeModel.setCommentId(commentId);
		cmtLikeModel.setGroupId(groupId);
		cmtLikeModel.setImgId(imgId);
		cmtLikeModel.setLikeOrDislike(likeOrDislike);
		cmtLikeModel.setLikeUserId(likeUserId);
		cmtLikeModel.setUserId(userId);
		
		if (likeUserId.equals(userId)) {
			map.put(ApiConstant.ErrorMsg, "不能给自己的评论点赞");
			return map;
		}
		
		try {
			List<ImgCmtLikeModel> list = commentService.findCommentLikeIsExistByCommentId(commentId, likeUserId);
			if (list.size() != 0) {
				map.put(ApiConstant.ErrorMsg, "已经赞过或踩过啦...");
			}else{
				if (commentLike+commentDislike == 1) {
					try {
						int res1 = commentService.addCommentLikeWithUser(cmtLikeModel);
						if (res1 > 0) {
							int res2 = commentService.commentLikeAndDislike(commentLike, commentDislike, id);
							if (res2 > 0) {
								map.put(ApiConstant.Success, true);
							}else{
								map.put(ApiConstant.Success, false);
							}
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
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		return map;
	}
}
