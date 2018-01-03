package com.weChat.bean.tag;

/**
 * 管理用户的标签-bean
 * 
 * @author 陈俊华
 * @date 2017年12月26日
 */
public class Tag {

	// 标签id，由微信分配
	private int id;

	// 标签名，UTF8编码
	private String name;

	public Tag() {
	}

	public Tag(int id) {
		this.id = id;
	}

	public Tag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
