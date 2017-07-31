package com.k4meitu.pic.po;
/*
 * CREATE TABLE img_comment(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	groupId VARCHAR (20) NOT NULL COMMENT '图组的ID',
	imgId INT (15) NOT NULL COMMENT '图片的ID',
	userId varchar(32) NOT NULL COMMENT '用户的id',
	imgComment VARCHAR(150) DEFAULT '' COMMENT '图片的评论文字',
	isCommentShow INT(1) DEFAULT 0 COMMENT '评论是否显示',
	date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据插入时间'
);
 * 
 * */

import java.sql.Timestamp;


public class CommentModel {
	private int id;
	private String groupId;
	private int imgId;
	private String userId;
	private String imgComment;
	private int commentLike;
	private int commentDislike;
	private int isCommentShow;
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
	public String getImgComment() {
		return imgComment;
	}
	public void setImgComment(String imgComment) {
		this.imgComment = imgComment;
	}
	public int getIsCommentShow() {
		return isCommentShow;
	}
	public void setIsCommentShow(int isCommentShow) {
		this.isCommentShow = isCommentShow;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}
	public int getCommentDislike() {
		return commentDislike;
	}
	public void setCommentDislike(int commentDislike) {
		this.commentDislike = commentDislike;
	}
	
}
