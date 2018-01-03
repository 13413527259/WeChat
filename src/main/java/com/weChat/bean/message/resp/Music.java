package com.weChat.bean.message.resp;

/**
 * 响应消息-音乐消息-音乐
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class Music {

	// 音乐标题
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐链接
	private String MusicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HMusicUrl;
	// 缩略图id
	private String ThumbMediaId;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHMusicUrl() {
		return HMusicUrl;
	}

	public void setHMusicUrl(String hMusicUrl) {
		HMusicUrl = hMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
