package com.k4meitu.pic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.k4meitu.pic.mapper.ArticleMapper;
import com.k4meitu.pic.po.SexyArticleModel;
import com.k4meitu.pic.service.ArticleService;

public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public List<SexyArticleModel> getLatestArticles(int curPage, int pCount) throws Exception {
		// TODO Auto-generated method stub
		return articleMapper.getLatestArticles(curPage, pCount);
	}

	@Override
	public int getAllLatestArticles() throws Exception {
		// TODO Auto-generated method stub
		return articleMapper.getAllLatestArticles();
	}

	@Override
	public List<SexyArticleModel> getLatestArticlesByType(String type, String subType, int curPage, int pCount)
			throws Exception {
		// TODO Auto-generated method stub
		return articleMapper.getLatestArticlesByType(type, subType, curPage, pCount);
	}

	@Override
	public int getAllLatestArticlesByType(String type, String subType) throws Exception {
		// TODO Auto-generated method stub
		return articleMapper.getAllLatestArticlesByType(type, subType);
	}

}
