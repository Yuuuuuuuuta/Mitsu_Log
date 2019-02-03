package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception{

		//静的リソースにはセキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{

		//ログイン不要ページの設定
		http
		.authorizeRequests()
		.antMatchers("/webjars/**").permitAll()//webjarsへのアクセス許可
		.antMatchers("/css/**").permitAll()//cssへのアクセス許可
		.antMatchers("/login").permitAll()//ログインページは直リンク可
		.antMatchers("/signup").permitAll()//ユーザー登録画面は直リンク可
		.anyRequest().authenticated();

		//ログイン処理
		http
		.formLogin()
		.loginProcessingUrl("/login")//ログイン処理のパス
		.loginPage("/login")//ログインページの視点
		.failureUrl("/login")//ログイン失敗時の遷移先
		.usernameParameter("userId")
		.passwordParameter("password")
		.defaultSuccessUrl("/home",true);//ログイン成功時の遷移先

		//CSRF対策を向こうに設定(一時的)
		http.csrf().disable();

	}

}
