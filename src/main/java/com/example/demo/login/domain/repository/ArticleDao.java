package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Article;

public interface ArticleDao {

	//Userテーブルの件数取得
	public int count() throws DataAccessException;

	//Userテーブルにデータを1件挿入
	public int insertOne(Article article) throws DataAccessException;

	//Userテーブルのデータを1件取得
	public Article selectOne(String articleId) throws DataAccessException;

	//Userテーブルのデータを全件取得
	public List<Article> selectMany() throws DataAccessException;

	//Userテーブルを1件更新
	public int updateOne(Article article) throws DataAccessException;

	//Userテーブルを1件削除
	public int deleteOne(String string) throws DataAccessException;


}
