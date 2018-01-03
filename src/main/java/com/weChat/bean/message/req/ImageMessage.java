package com.weChat.bean.message.req;

/**
 * 请求信息-图片消息
 * 
 * @author 13413527259
 *
 */
public class ImageMessage extends BaseMessage {

	// 图片链接
	private String PicUrl;

	// 媒体Id
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public ImageMessage(String toUserName, String fromUserName, long createTime, String msgType, long msgId,
			String picUrl, String mediaId) {
		super(toUserName, fromUserName, createTime, msgType, msgId);
		PicUrl = picUrl;
		MediaId = mediaId;
	}

	public ImageMessage() {
	}

}
