package com.k4meitu.pic.po;

import java.sql.Timestamp;

public class PicGroupModel {
	private int  id;
	private String  groupId;
	private String title;
	private int count;
	private String type;
	private String imgUrl;
	private Timestamp date;
	private String imgCoverName;
	private int imgCoverHeight;
	private int imgCoverWidth;
	private int browseCount;
	private int score;
	private int vip;
	
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgCoverName() {
		return imgCoverName;
	}
	public void setImgCoverName(String imgCoverName) {
		this.imgCoverName = imgCoverName;
	}
	public int getImgCoverHeight() {
		return imgCoverHeight;
	}
	public void setImgCoverHeight(int imgCoverHeight) {
		this.imgCoverHeight = imgCoverHeight;
	}
	public int getImgCoverWidth() {
		return imgCoverWidth;
	}
	public void setImgCoverWidth(int imgCoverWidth) {
		this.imgCoverWidth = imgCoverWidth;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}