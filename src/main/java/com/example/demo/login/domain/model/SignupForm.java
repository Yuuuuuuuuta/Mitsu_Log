package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	@Email
	private String userId;

	@NotBlank
	@Length(min = 4,max = 10)
	private String password;

	@NotBlank
	private String userName;

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;

	@Min(18)
	@Max(100)
	private int age;

	private boolean marriage;


}
