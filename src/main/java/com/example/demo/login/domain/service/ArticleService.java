package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.repository.ArticleDao;

@Service
public class ArticleService {

	@Autowired
	ArticleDao dao ;

	//追加用
	public boolean insert(Article article) {

		int rowNumber = dao.insertOne(article);

		boolean result = false;

		if (rowNumber > 0) {
			result = true;
		}

		return result;

	}

	//カウント用
	public int count() {
		return dao.count();
	}

	public List<Article> selectMany(){

		return dao.selectMany();

	}

	//1件取得
	public Article selectOne(String articleId) {

		return dao.selectOne(articleId);
	}

	//1件更新用
	public boolean updateOne(Article article) {

		int rowNumber = dao.updateOne(article);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}
		return result;
	}

	//1件削除用
	public boolean deleteOne(String articleId) {
		int rowNumber = dao.deleteOne(articleId);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}

		return result;

	}



}
