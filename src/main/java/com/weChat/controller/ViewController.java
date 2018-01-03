package com.weChat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.weChat.util.QRCodeUtil;

@Controller
public class ViewController {

	@GetMapping("/getQRCode")
	public String getQRCode(HttpServletRequest request) throws Exception {
		request.setAttribute("src",
				QRCodeUtil.longToShort(QRCodeUtil.getQRCodeUrl(QRCodeUtil.createQRCode(22).getTicket())));
		return "QRCode";
	}

}
