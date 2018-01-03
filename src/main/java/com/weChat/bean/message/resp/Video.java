package com.weChat.bean.message.resp;

/**
 * 响应消息-视频消息-视频
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class Video {

	// 媒体id
	private String MediaId;
	// 缩略图id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
