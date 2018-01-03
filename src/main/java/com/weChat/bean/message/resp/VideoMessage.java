package com.weChat.bean.message.resp;

/**
 * 响应消息-视频消息
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class VideoMessage extends BaseMessage {

	// 响应的视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
