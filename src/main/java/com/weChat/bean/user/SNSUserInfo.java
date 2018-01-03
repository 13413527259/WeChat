package com.weChat.bean.user;

import java.util.Date;
import java.util.List;

/**
 * 网页授权获取的用户信息
 * 
 * @author 陈俊华
 * @date 2017年12月15日
 */
public class SNSUserInfo {

	// 是否关注(1关注,0)
	private int subscribe;

	// 关注时间
	private Date subscribe_time;

	private String openid;

	// 昵称
	private String nickname;

	// 性别(1男,2女,0未知)
	private int sex;

	// 国家
	private String country;

	// 省份
	private String province;
	// 城市
	private String city;

	// 语言，简体中文为zh_CN
	private String language;

	// 头像地址
	private String headimgurl;

	// 用户特权信息
	private List<String> privilege;

	private String unionid;

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Override
	public String toString() {
		return "SNSUserInfo [subscribe=" + subscribe + ", subscribe_time=" + subscribe_time + ", openid=" + openid
				+ ", nickname=" + nickname + ", sex=" + sex + ", country=" + country + ", province=" + province
				+ ", city=" + city + ", language=" + language + ", headimgurl=" + headimgurl + ", privilege="
				+ privilege + ", unionid=" + unionid + "]";
	}

}
