package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ArticleForm {

	@NotBlank
	private String articleId;

	private String title;

	private String memo;

	private String category;

	private Date postDate;

}
