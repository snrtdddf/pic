package com.k4meitu.pic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.CommentModel;
import com.k4meitu.pic.po.ImgCmtLikeModel;
import com.k4meitu.pic.po.KeywordListModel;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.po.PicGroupMostLikeModel;

public interface CommentMapper {
	//增加评论
	public void addComment(CommentModel commentModel) throws Exception;
	
	//获取评论
	public List<CommentModel> getCommentByGroupId(
			@Param("groupId")String groupId,
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	//获取评论的总数
	public int getCommentCountByGroupId(@Param("groupId")String groupId) throws Exception;
	
	//查询是否已经点赞
	public List<LikeModel> findLikeIsExist(LikeModel likeModel) throws Exception;
	
	//点赞
	public int addLike(LikeModel likeModel) throws Exception;
	
	//查询点赞数
	public int getPicGroupLikeCount(@Param("groupId")String groupId) throws Exception;
	
	//最多评论
	public List<PicGroupMostLikeModel> findMostLikeGroup() throws Exception;
	
	//评论点赞
	public int addCommentLikeAndDislike(@Param("like")int commentLike,
										 @Param("Dislike")int commentDislike,
										 @Param("id")int id
										 ) throws Exception;
	
	//GIF图组点赞
	public int addGifPicGroupLikeAndDislike(@Param("like")int picGroupLike,
			 @Param("dislike")int picGroupDislike,
			 @Param("id")int id
			 ) throws Exception;
	
	//评论点赞with用户
	public int addCommentLikeWithUser (ImgCmtLikeModel imgCmtLikeModel) throws Exception;
	
	//查询用户是否对该评论点赞了
	public List<ImgCmtLikeModel> findCommentLikeIsExistByCommentId(
			@Param("commentId")int commentId,
			@Param("likeUserId")String likeUserId
			) throws Exception;
}
