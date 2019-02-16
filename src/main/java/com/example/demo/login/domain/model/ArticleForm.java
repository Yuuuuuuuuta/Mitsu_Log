package com.example.demo.login.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleForm {

	private String articleId;

	private String title;

	private String memo;

	private String category;

	private Date postDate;

}
