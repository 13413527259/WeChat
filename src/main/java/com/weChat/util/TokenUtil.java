package com.weChat.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weChat.bean.token.Token;
import com.weChat.service.TokenService;

import net.sf.json.JSONObject;

/**
 * 缓存/更新Token-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
@Component
public class TokenUtil {

	private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

	/**
	 * 因为@Autowired不能注解静态
	 * 
	 * @param service
	 */
	@Autowired
	public TokenUtil(TokenService service) {
		TokenUtil.service = service;
	}

	// 用于定时刷新Token后保存到数据库
	private static TokenService service;

	/**
	 * 查询Token
	 * 
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws Exception
	 */
	public static Token getToken(String appid, String appsecret) throws Exception {
		// 官方链接
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		// 修改appID，secret
		tokenUrl = tokenUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 模拟请求取得结果
		JSONObject jsonObject = HttpsUtil.httpRequest(tokenUrl, MethodType.GET, null);
		logger.info("微信api查询token，时间={}", new Date());
		logger.info("respBody={}", jsonObject);
		return (Token) JSONObject.toBean(jsonObject, Token.class);
	}

	/**
	 * 查询Token
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Token getTokenInDB() throws Exception {
		Token token = service.findByIdLimitOne();
		if (token != null) {
			long lastTime = 7200000 - (new Date().getTime() - token.getCreateTime().getTime());
			if (lastTime < 1) {
				token = getToken(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue());
				token.setCreateTime(new Date());
				service.save(token);
				System.err.println("getTokenInDB发现token过期，所以保存了新的token：" + token);
			}
		}
		return token;
	}

	/**
	 * 定时刷新Token
	 * 
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 3600000)
	public void putToken() throws Exception {

		// 为防止非正常刷新，设定判断剩余时长 剩余时长=7200000-(当前时间-createTime)。 如果剩余时长接近60000才可刷新
		Token old = service.findByIdLimitOne();
		if (old != null) {
			long lastTime = 7200000 - (new Date().getTime() - old.getCreateTime().getTime());
			if (lastTime > 60000) {
				logger.info("尝试刷新token，但被拦截了，token剩余有效时长：={}" + lastTime);
				return;
			}
		}
		logger.info("定时刷新Token，当前时间：={}" + new Date());
		Token token = getToken(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue());
		token.setCreateTime(new Date());
		service.save(token);
	}

}
