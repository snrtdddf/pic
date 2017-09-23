package com.k4meitu.pic.po;

import java.sql.Timestamp;

public class ImgCmtLikeModel {
	
	private int id;
	private int commentId;
	private String groupId;
	private int imgId;
	private String userId;
	private String likeUserId;
	private int likeOrDislike;
	private Timestamp date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(String likeUserId) {
		this.likeUserId = likeUserId;
	}
	
	public int getLikeOrDislike() {
		return likeOrDislike;
	}
	public void setLikeOrDislike(int likeOrDislike) {
		this.likeOrDislike = likeOrDislike;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
