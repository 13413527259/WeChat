package com.weChat.bean.message.req;

/**
 * 请求消息-视频消息
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class VideoMessage extends BaseMessage{

	// 媒体id
	private String MediaId;

	// 视频缩略图的媒体id
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
