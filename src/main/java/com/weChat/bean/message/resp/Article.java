package com.weChat.bean.message.resp;

/**
 * 响应消息-图文消息-图文
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class Article {

	// 图文名称
	private String Title;
	// 图文描述
	private String Description;
	// 图片链接
	private String PicUrl;
	// 图文链接
	private String Url;

	public String getTiTle() {
		return Title;
	}

	public void setTiTle(String tiTle) {
		Title = tiTle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
