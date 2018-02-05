package com.weChat.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weChat.bean.token.Token;
import com.weChat.bean.user.SNSUserInfo;
import com.weChat.service.Oath2Service;
import com.weChat.service.TokenService;
import com.weChat.util.MenuUtil;
import com.weChat.util.UserUtil;

@RestController
public class IndexController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private Oath2Service oauth2Service;

	@GetMapping("/index")
	public String hello() {
		return "hello !this is my service!";
	}

	@GetMapping("/token")
	public Token getToken() throws Exception {
		return tokenService.findByIdLimitOne();
	}

	@GetMapping("/oauth2")
	public SNSUserInfo hello(@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "state", required = false) String state) throws Exception {
		System.err.println("code:" + code);
		// 同意用户授权
		if (code != null && code != "") {
			return oauth2Service.getUserInfo(code);
		}
		return null;
	}

	@GetMapping("toLogin")
	public void redrict(HttpServletResponse response) throws Exception {
		String url = UserUtil.getLoginUrl("index");
		response.sendRedirect(url);
	}
	
	@GetMapping("createMenu")
	public int createMenu() throws Exception {
		return MenuUtil.createMenu();
	}

}
