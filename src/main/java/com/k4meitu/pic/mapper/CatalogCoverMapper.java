package com.k4meitu.pic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.GifGroupPicNewestModel;
import com.k4meitu.pic.po.ImgPropModel;
import com.k4meitu.pic.po.KeywordListModel;
import com.k4meitu.pic.po.MenuBtnModel;
import com.k4meitu.pic.po.PicGroupModel;

public interface CatalogCoverMapper {
	//根据id来获取一组图
	public PicGroupModel getCatalogCoverById(int id) throws Exception;
	
	//获取当前表的数据总数
	public int getAllCatalogCover() throws Exception;
	
	//分页获取组图，从最新的图开始
	public List<PicGroupModel> getCatalogCoverListInPage(
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	
	
	//根据关键字查询
	public List<PicGroupModel> findGroupByKeyword(
			@Param("keyword")String keyword,
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	//根据关键字查询返回总数量
	public int getGroupCountOfKeyword(@Param("keyword")String keyword) throws Exception;
	
	//获取图组详情
	public List<ImgPropModel> findPicDetailByGroupId(@Param("groupId")String groupId) throws Exception;
	
	//获取图组（通过groupId）
	public List<PicGroupModel> findPicGroupByGroupId(@Param("groupId")String groupId) throws Exception;

	//获取关键字列表
	public List<KeywordListModel> getKeywordList(
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	//获取搜索次数最多的关键字列表
	public List<KeywordListModel> getKeywordListBySearchCount() throws Exception;
	
	//获取关键字的数量
	public int getKeywordListCount() throws Exception;
	
	//按浏览量排序
	public List<PicGroupModel> findPicGroupBrowseMax(
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	//按分数排序
	public List<PicGroupModel> findPicGroupScoreMax(
				@Param("curPage")int curPage,
				@Param("pCount")int pCount
				) throws Exception;
	
	//查询菜单
	public List<MenuBtnModel> getGroupMenuBtn() throws Exception;
	
	//*********************************GIF*************************
	//获取最新的GIF图
	public List<GifGroupPicNewestModel> getGifGroupPicNewest(
			@Param("type")String type,
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	//获取GIF图总数
	public int getAllGifGroupPicNewest() throws Exception;
}
