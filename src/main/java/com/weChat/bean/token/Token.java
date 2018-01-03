package com.weChat.bean.token;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Token凭证
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
@Entity
public class Token {

	@Override
	public String toString() {
		return "Token [id=" + id + ", createTime=" + create_time + ", access_token=" + access_token + ", expires_in="
				+ expires_in + "]";
	}

	// 表拓展字段
	@Id
	@GeneratedValue
	private int id;
	private Date create_time;

	// 获取到的凭证
	private String access_token;
	// 凭证有效时间，单位：秒
	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return create_time;
	}

	public void setCreateTime(Date createTime) {
		this.create_time = createTime;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

}
