package com.k4meitu.pic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k4meitu.pic.mapper.CatalogCoverMapper;
import com.k4meitu.pic.po.GifGroupPicNewestModel;
import com.k4meitu.pic.po.ImgPropModel;
import com.k4meitu.pic.po.KeywordListModel;
import com.k4meitu.pic.po.MenuBtnModel;
import com.k4meitu.pic.po.PicGroupModel;
import com.k4meitu.pic.service.CatalogCoverService;

@Service
public class CatalogCoverServiceImpl implements CatalogCoverService{

	@Autowired
	private CatalogCoverMapper catalogcoverMapper;
	
	@Override
	public PicGroupModel getCatalogCoverById(int id) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getCatalogCoverById(id);
	}

	@Override
	public List<ImgPropModel> findPicDetailByGroupId(String groupId) throws Exception {
		
		return catalogcoverMapper.findPicDetailByGroupId(groupId);
	}

	@Override
	public int getAllCatalogCover() throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getAllCatalogCover();
	}

	@Override
	public List<PicGroupModel> getCatalogCoverListInPage(int curPage, int pCount) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getCatalogCoverListInPage(curPage, pCount);
	}
	
	@Override
	public int getGroupCountOfKeyword(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getGroupCountOfKeyword(keyword);
	}


	@Override
	public List<PicGroupModel> findGroupByKeyword(String keyword, int curPage, int pCount) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.findGroupByKeyword(keyword, curPage, pCount);
	}
	//获取关键字列表
	@Override
	public List<KeywordListModel> getKeywordList(int curPage,int pCount) throws Exception{
		return catalogcoverMapper.getKeywordList(curPage, pCount);
	}
	
	//获取关键字的个数
	@Override
	public int getKeywordListCount() throws Exception{
		return catalogcoverMapper.getKeywordListCount();
	}

	@Override
	public List<KeywordListModel> getKeywordListBySearchCount() throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getKeywordListBySearchCount();
	}

	@Override
	public List<PicGroupModel> findPicGroupBrowseMax(int curPage, int pCount) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.findPicGroupBrowseMax(curPage, pCount);
	}

	@Override
	public List<PicGroupModel> findPicGroupScoreMax(int curPage, int pCount) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.findPicGroupScoreMax(curPage, pCount);
	}

	@Override
	public List<MenuBtnModel> getGroupMenuBtn() throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getGroupMenuBtn();
	}

	@Override
	public List<GifGroupPicNewestModel> getGifGroupPicNewest(int curPage,int pCount) throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getGifGroupPicNewest(curPage,pCount);
	}

	@Override
	public int getAllGifGroupPicNewest() throws Exception {
		// TODO Auto-generated method stub
		return catalogcoverMapper.getAllGifGroupPicNewest();
	}
	

	

}
