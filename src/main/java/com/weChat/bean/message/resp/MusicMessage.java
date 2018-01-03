package com.weChat.bean.message.resp;

/**
 * 响应消息-音乐消息
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class MusicMessage extends BaseMessage{

	// 响应的音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
