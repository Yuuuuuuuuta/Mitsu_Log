package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;



@Service
public class UserService {

	@Autowired
	UserDao dao;

	//追加用
	public boolean insert(User user) {

		int rowNumber = dao.insertOne(user);

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

	public List<User> selectMany(){

		return dao.selectMany();

	}

	//1件取得
	public User selectOne(String userId) {

		return dao.selectOne(userId);
	}

	//1件更新用
	public boolean updateOne(User user) {

		int rowNumber = dao.updateOne(user);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}
		return result;
	}

	//1件削除用
	public boolean deleteOne(String userId) {
		int rowNumber = dao.deleteOne(userId);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}

		return result;

	}



}
