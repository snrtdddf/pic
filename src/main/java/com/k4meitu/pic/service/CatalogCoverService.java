package com.k4meitu.pic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.GifGroupPicNewestModel;
import com.k4meitu.pic.po.ImgPropModel;
import com.k4meitu.pic.po.KeywordListModel;
import com.k4meitu.pic.po.MenuBtnModel;
import com.k4meitu.pic.po.PicGroupModel;


public interface CatalogCoverService {
		//根据id来获取一组图
		public PicGroupModel getCatalogCoverById(int id) throws Exception;
		
		//获取当前表的数据总数
		public int getAllCatalogCover() throws Exception;
		
		//分页获取组图，从最新的图开始
		public List<PicGroupModel> getCatalogCoverListInPage(
				int curPage,
				int pCount
				) throws Exception;
		
		
		
		//根据关键字查询
		public List<PicGroupModel> findGroupByKeyword(
				String keyword,
				int curPage,
				int pCount
				) throws Exception;
		
		//根据关键字查询返回总数量
		public int getGroupCountOfKeyword(String keyword) throws Exception;
		
		//获取图组详情
		public List<ImgPropModel> findPicDetailByGroupId(String groupId) throws Exception;
		
		//获取关键字列表
		public List<KeywordListModel> getKeywordList(int curPage,int pCount) throws Exception;
		
		//获取关键字的个数
		public int getKeywordListCount() throws Exception;
		
		//获取搜索次数最多的关键字列表
		public List<KeywordListModel> getKeywordListBySearchCount() throws Exception;
		
		//按浏览量排序
		public List<PicGroupModel> findPicGroupBrowseMax(
				int curPage,
				int pCount
				) throws Exception;
		//按分数排序
		public List<PicGroupModel> findPicGroupScoreMax(
				int curPage,
				int pCount
				) throws Exception;
		
		//查询菜单
		public List<MenuBtnModel> getGroupMenuBtn() throws Exception;
		
		//********************************GIF********************************
		public List<GifGroupPicNewestModel> getGifGroupPicNewest(String type, int curPage,int pCount) throws Exception;
		//获取GIF图总数
		public int getAllGifGroupPicNewest() throws Exception;
}
