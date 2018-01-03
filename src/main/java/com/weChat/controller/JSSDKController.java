package com.weChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JSSDKController {

	/**
	 * 扫一扫
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/scan")
	public String scanQR() throws Exception {
		return "home";
	}

}
