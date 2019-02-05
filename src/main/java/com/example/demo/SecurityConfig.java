package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	//ユーザーIDとパスワードの取得
	private static final String USER_SQL = "SELECT"
			+ " user_id,"
			+ " password,"
			+ " true"
			+ " FROM"
			+ " m_user"
			+ " WHERE"
			+ " user_id = ?";

	//ユーザーのロール情報取得
	private static final String ROLE_SQL = "SELECT"
			+ " user_id,"
			+ " role"
			+ " FROM"
			+ " m_user"
			+ " WHERE"
			+ " user_id = ?";

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

		//ログアウト処理
		http
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");

		//CSRF対策を無効に設定(一時的)
		//http.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(USER_SQL)
		.authoritiesByUsernameQuery(ROLE_SQL)
		.passwordEncoder(passwordEncoder());
	}

}
