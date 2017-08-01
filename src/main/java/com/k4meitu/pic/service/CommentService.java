package com.k4meitu.pic.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.CommentModel;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.po.MenuBtnModel;


public interface CommentService {
	//增加评论
	public void addComment(CommentModel commentModel) throws Exception;
		
	//评论点赞
	public int commentLikeAndDislike(
			int commentLike, 
			int commentDislike, 
			int id 
			) throws Exception;
	
	//获取评论
	public List<CommentModel> getCommentByGroupId(
			String groupId,
			int curPage,
			int pCount
			) throws Exception;
	
	//获取评论的总数
	public int getCommentCountByGroupId(String groupId) throws Exception;
	
	//查询是否已经点赞
	public List<LikeModel> findLikeIsExist(LikeModel likeModel) throws Exception;
		
	//点赞
	public int addLike(LikeModel likeModel) throws Exception;
			
	//最多评论
	public Map<String, Object> findMostLikeGroup() throws Exception;
	
	//获取图组点赞总数
	public int getPicGroupLikeCount(String groupId) throws Exception;
	
	//****************************GIF********************************
	//GIF图组点赞
	public int addGifPicGroupLikeAndDislike(@Param("like")int picGroupLike,
				 @Param("dislike")int picGroupDislike,
				 @Param("id")int id
				 ) throws Exception;
}
