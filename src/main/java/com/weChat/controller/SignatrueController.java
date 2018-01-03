package com.weChat.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weChat.util.MessageUtil;
import com.weChat.util.SignatureUtil;

/**
 * 检验签名
 * @author 13413527259
 *
 */
@RestController
public class SignatrueController {

	@GetMapping("/checkSignature")
	public String checkSignatrue(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr) {
		return SignatureUtil.isSignature(signature, timestamp, nonce)?echostr:null;
	}
	
	@PostMapping("/checkSignature")
	public String checkSignatrue(HttpServletRequest request) throws Exception {
		// 调用核心业务类接收消息、处理消息
		return MessageUtil.parseRequest(request);
	}
	
}
