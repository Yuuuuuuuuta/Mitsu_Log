package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

	//Userテーブルの件数取得
	public int count() throws DataAccessException;

	//Userテーブルにデータを1件挿入
	public int insertOne(User user) throws DataAccessException;

	//Userテーブルのデータを1件取得
	public User selectOne(String userId) throws DataAccessException;

	//Userテーブルのデータを全件取得
	public List<User> selectMany() throws DataAccessException;

	//Userテーブルを1件更新
	public int updateOne(User user) throws DataAccessException;

	//Userテーブルを1件削除
	public int deleteOne(String string) throws DataAccessException;

	//結果をサーバーにCSVファイルで保存する
	public void userCsvOut() throws DataAccessException;


}
