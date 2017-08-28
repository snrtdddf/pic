package com.k4meitu.pic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.SexyArticleModel;

public interface ArticleMapper {
	//获取最新的文章
	public List<SexyArticleModel> getLatestArticles(
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	
	//获取所有的文章数量
	public int getAllLatestArticles() throws Exception;
	
	
	//分类获取最新的文章
	public List<SexyArticleModel> getLatestArticlesByType(
			@Param("type")String type,
			@Param("subType")String subType,
			@Param("curPage")int curPage,
			@Param("pCount")int pCount
			) throws Exception;
	//分类获取所有的文章数量
	public int getAllLatestArticlesByType(
			@Param("type")String type,
			@Param("subType")String subType
			) throws Exception;
	
}
