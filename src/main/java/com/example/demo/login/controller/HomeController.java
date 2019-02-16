package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Article;
import com.example.demo.login.domain.model.ArticleForm;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.ArticleService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	private Map<String,String> radioMarriage;

	private Map<String,String> initRadioMarriage(){

		Map<String,String> radio = new LinkedHashMap<>();

		radio.put("既婚","true");
		radio.put("未婚","false");

		return radio;
	}

	@Autowired
	ArticleService articleService;


	@GetMapping("/home")
	public String getHome(Model model) {

		model.addAttribute("contents", "login/home :: home_contents");

		return "login/homeLayout";

	}

	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "login/userList :: userList_contents");

		List<User> userList = userService.selectMany();

		model.addAttribute("userList", userList);

		int count = userService.count();
		model.addAttribute("userListCount", count);

		return "login/homeLayout";
	}

	@GetMapping("/userDetail/{id:.+}")
	public String getuserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		System.out.println("userId=" + userId);

		model.addAttribute("contents", "login/userDetail :: userDetail_contents");

		radioMarriage = initRadioMarriage();

		model.addAttribute("radioMarriage", radioMarriage);

		if(userId != null && userId.length() > 0) {

			User user = userService.selectOne(userId);

			form.setUserId(user.getUserId());
			form.setPassword(user.getPassword());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());

			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}

	@GetMapping("/articleList")
	public String getArticleList(Model model) {
		model.addAttribute("contents", "login/articleList :: articleList_contents");

		List<Article> articleList = articleService.selectMany();

		model.addAttribute("articleList", articleList);

		int count = userService.count();
		model.addAttribute("articleListCount", count);

		return "login/homeLayout";
	}

	@GetMapping("/articleDetail/{id}")
	public String getarticleDetail(@ModelAttribute ArticleForm form, Model model, @PathVariable("id") String articleId) {
		System.out.println("articleId=" + articleId);

		model.addAttribute("contents", "login/articleDetail :: articleDetail_contents");

		if(articleId != null && articleId.length() > 0) {

			Article article = articleService.selectOne(articleId);

			form.setArticleId(article.getArticleId());
			form.setTitle(article.getTitle());
			form.setMemo(article.getMemo());
			form.setCategory(article.getCategory());
			form.setPostDate(article.getPostDate());

			model.addAttribute("articleForm", form);
		}
		return "login/homeLayout";
	}

	@GetMapping("/newArticle")
	public String getNewArticle(@ModelAttribute ArticleForm form, Model model) {

		model.addAttribute("contents", "login/newArticle :: newArticle_contents");

		return "login/homeLayout";
	}

	@PostMapping(value="/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");

		User user = new User();

		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());


		boolean result = userService.updateOne(user);

		if(result == true) {
			model.addAttribute("result", "更新成功");
		}else {
			model.addAttribute("result", "更新失敗");
		}

		return getUserList(model);

	}

	@PostMapping(value="/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");

		boolean result = userService.deleteOne(form.getUserId());

		if(result == true) {
			model.addAttribute("result", "削除成功");
		}else {
			model.addAttribute("result", "削除失敗");
		}

		return getUserList(model);
	}


	@PostMapping("/newArticle")
	public String postNewArticle(@ModelAttribute @Validated ArticleForm form, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {

			return getNewArticle(form,model);
		}

		System.out.println(form);

		Article article = new Article();

		article.setArticleId(form.getArticleId());
		article.setTitle(form.getTitle());
		article.setMemo(form.getMemo());
		article.setCategory(form.getCategory());
		article.setPostDate(form.getPostDate());

		boolean result = articleService.insert(article);

		if(result == true) {
			System.out.println("insert成功");
		}else {
			System.out.println("insert失敗");
		}

		return "redirect:/articleList";

	}


	@PostMapping("/logout")
	public String postLogout() {

		return "redirect:/login";
	}

	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getUserList(model);
	}

	//アドミン専用画面
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");

		return "login/homeLayout";
	}

}
