package com.weChat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSSDKTestAop {

	@GetMapping("/aop/{id}")
	public String aop(@PathVariable("id") Integer id, HttpServletRequest request) {
		return request.getAttribute("appid").toString() + ",id:" + id;
	}

}
