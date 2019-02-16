package com.example.demo.login.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {

	private String articleId;
	private String title;
	private String memo;
	private String category;
	private Date postDate;

}
