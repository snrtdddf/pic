package com.k4meitu.pic.service;

import java.util.List;

import com.k4meitu.pic.po.SexyArticleModel;

public interface ArticleService {
		//获取最新的文章
		public List<SexyArticleModel> getLatestArticles(int curPage,int pCount) throws Exception;
		
		//获取所有的文章数量
		public int getAllLatestArticles() throws Exception;
}
