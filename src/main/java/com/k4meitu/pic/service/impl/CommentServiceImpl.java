package com.k4meitu.pic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.mapper.CatalogCoverMapper;
import com.k4meitu.pic.mapper.CommentMapper;
import com.k4meitu.pic.po.CommentModel;
import com.k4meitu.pic.po.ImgCmtLikeModel;
import com.k4meitu.pic.po.LikeModel;
import com.k4meitu.pic.po.PicGroupModel;
import com.k4meitu.pic.po.PicGroupMostLikeModel;
import com.k4meitu.pic.service.CommentService;

public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private CatalogCoverMapper picGroupMapper;
	
	@Override
	public void addComment(CommentModel commentModel) throws Exception {
		commentMapper.addComment(commentModel);
	}

	@Override
	public List<CommentModel> getCommentByGroupId(
			@Param("groupId")String groupId,
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception{
		
		return commentMapper.getCommentByGroupId(groupId, curPage, pCount);
	}

	@Override
	public int getCommentCountByGroupId(String groupId) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getCommentCountByGroupId(groupId);
	}

	@Override
	public List<LikeModel> findLikeIsExist(LikeModel likeModel) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.findLikeIsExist(likeModel);
	}

	@Override
	public int addLike(LikeModel likeModel) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.addLike(likeModel);
	}

	@Override
	public Map<String, Object> findMostLikeGroup() throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		
		List<PicGroupMostLikeModel> list = commentMapper.findMostLikeGroup();
		
		List<PicGroupModel> picGroupList = new ArrayList<>();
		if (list.size() != 0) {
			for (int i = 0; i < list.size() ; i++) {
				PicGroupMostLikeModel model = list.get(i);
				List<PicGroupModel> temp = picGroupMapper.findPicGroupByGroupId(model.getGroupId());
				if (temp.size() != 0) {
					PicGroupModel picGroupModel = temp.get(0);
					picGroupList.add(picGroupModel);
				}
			}
			
			map.put("picGroupList", picGroupList);
			map.put("mostCommentList", list);
			return map;
		}
		
		return null;
	}

	@Override
	public int getPicGroupLikeCount(String groupId) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getPicGroupLikeCount(groupId);
	}

	@Override
	public int commentLikeAndDislike(int commentLike, int commentDislike, int id)
			throws Exception {
		return commentMapper.addCommentLikeAndDislike(commentLike, commentDislike, id);
	}

	@Override
	public int addGifPicGroupLikeAndDislike(int picGroupLike, int picGroupDislike, int id) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.addGifPicGroupLikeAndDislike(picGroupLike, picGroupDislike, id);
	}

	@Override
	public int addCommentLikeWithUser(ImgCmtLikeModel imgCmtLikeModel) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.addCommentLikeWithUser(imgCmtLikeModel);
	}

	@Override
	public List<ImgCmtLikeModel> findCommentLikeIsExistByCommentId(int commentId, String likeUserId)
			throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.findCommentLikeIsExistByCommentId(commentId, likeUserId);
	}
	
	

}
