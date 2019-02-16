package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.repository.ArticleDao;


@Repository
public class ArticleDaoJdbcImpl implements ArticleDao{

	@Autowired
	JdbcTemplate jdbc ;


	@Override
	public int count() throws DataAccessException{

		int count = jdbc.queryForObject("SELECT COUNT(*) FROM article_list", Integer.class);

		return count;

	}

	@Override
	public int insertOne(Article article) throws DataAccessException{

		String sql = "INSERT INTO article_list(article_id,"
				+ "title,"
				+ "memo,"
				+ "category,"
				+ "post_date)"
				+ "VALUES(?,?,?,?,?)";

		int rowNumber = jdbc.update(sql,
				article.getArticleId(),
				article.getTitle(),
				article.getMemo(),
				article.getCategory(),
				article.getPostDate());

		return rowNumber;

	}

	@Override
	public Article selectOne(String articleId) throws DataAccessException{

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM article_list" + " WHERE article_id = ?",articleId);

		Article article = new Article();

		article.setArticleId((String)map.get("article_id"));
		article.setTitle((String)map.get("title"));
		article.setMemo((String)map.get("memo"));
		article.setCategory((String)map.get("category"));
		article.setPostDate((Date)map.get("post_date"));

		return article;
	}


	@Override
	public List<Article> selectMany() throws DataAccessException{

		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM article_list");

		List<Article> articleList = new ArrayList<>() ;

		for(Map<String, Object> map:getList) {
			Article article = new Article();

			article.setArticleId((String)map.get("article_id"));
			article.setTitle((String)map.get("title"));
			article.setMemo((String)map.get("memo"));
			article.setCategory((String)map.get("category"));
			article.setPostDate((Date)map.get("post_date"));

			articleList.add(article);
		}
		return articleList;

	}


	@Override
	public int updateOne(Article article) throws DataAccessException{

		String sql = "UPDATE article_list"
				+ " SET"
				+ " title = ?,"
				+ " memo = ?,"
				+ " category = ?,"
				+ " post_date = ?"
				+ " WHERE article_id = ?";

		int rowNumber = jdbc.update(sql,
				article.getTitle(),
				article.getMemo(),
				article.getCategory(),
				article.getPostDate(),
				article.getArticleId());

		return rowNumber;

	}

	@Override
	public int deleteOne(String articleId) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM article_list WHERE article_id = ?",articleId);

		return rowNumber;
	}

}
