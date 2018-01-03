package com.weChat.service;

import org.springframework.stereotype.Service;

import com.weChat.bean.token.Oauth2Token;
import com.weChat.bean.user.SNSUserInfo;
import com.weChat.util.TokenInfo;
import com.weChat.util.UserUtil;

/**
 * 网页授权-业务逻辑
 * 
 * @author 陈俊华
 * @date 2017年12月15日
 */
@Service
public class Oath2Service {

	/**
	 * 授权的到code，获取用户信息
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	public SNSUserInfo getUserInfo(String code) throws Exception {
		Oauth2Token oauth2Token=UserUtil.getOauth2Token(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue(), code);
		System.err.println(oauth2Token.toString());
		SNSUserInfo userInfo = UserUtil.getUserInfo(oauth2Token);
		return userInfo;
	}

}
