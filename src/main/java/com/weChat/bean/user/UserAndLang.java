package com.weChat.bean.user;

/**
 * 工具类的用例
 * 
 * @author 陈俊华
 * @date 2017年12月28日
 */
public class UserAndLang {

	private String openid;
	private String lang;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public UserAndLang(String openid, String lang) {
		this.openid = openid;
		this.lang = lang;
	}

	public UserAndLang(String openid) {
		this.openid = openid;
	}

	public UserAndLang() {
	}

}
