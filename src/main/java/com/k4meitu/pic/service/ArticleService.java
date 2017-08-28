package com.k4meitu.pic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k4meitu.pic.po.SexyArticleModel;

public interface ArticleService {
		//获取最新的文章
		public List<SexyArticleModel> getLatestArticles(int curPage,int pCount) throws Exception;
		
		//获取所有的文章数量
		public int getAllLatestArticles() throws Exception;
		
		//分类获取最新的文章
		public List<SexyArticleModel> getLatestArticlesByType(
				String type,
				String subType,
				int curPage,
				int pCount
				) throws Exception;
		//分类获取所有的文章数量
		public int getAllLatestArticlesByType(
				String type,
				String subType
				) throws Exception;
}
