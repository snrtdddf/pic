package com.k4meitu.pic.po;

import java.sql.Timestamp;
/*
 * id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	groupId VARCHAR (20) NOT NULL COMMENT '图组的ID',
	imgId INT (15) NOT NULL COMMENT '图片的ID',
	userId varchar(32) NOT NULL COMMENT '用户的id',
	imgLike int(1) DEFAULT 0 COMMENT '图片赞',
	imgDislike INT(1)  DEFAULT 0 COMMENT '图片不喜欢',
	date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据插入时间'
 * */
public class LikeModel {
	private int id;
	private String groupId;
	private int imgId;
	private String userId;
	private int imgLike;
	private int imgDislike;
	private Timestamp date;
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
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getImgLike() {
		return imgLike;
	}
	public void setImgLike(int imgLike) {
		this.imgLike = imgLike;
	}
	public int getImgDislike() {
		return imgDislike;
	}
	public void setImgDislike(int imgDislike) {
		this.imgDislike = imgDislike;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
