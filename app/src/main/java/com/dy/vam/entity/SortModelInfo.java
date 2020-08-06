package com.dy.vam.entity;

import java.io.Serializable;

public class SortModelInfo implements Serializable {

	private String iconUrl;
	private String name;
    private String sortLetters;
	private String post;
	private String percent;

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public int type;
	public static final int TYPE_CHECKED = 1;
	public static final int TYPE_NOCHECKED = 0;

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	private int id;

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	@Override
	public String toString() {
		return "SortModelInfo{" +
				"iconUrl='" + iconUrl + '\'' +
				", name='" + name + '\'' +
				", sortLetters='" + sortLetters + '\'' +
				", post='" + post + '\'' +
				", type=" + type +
				", id=" + id +
				'}';
	}

}