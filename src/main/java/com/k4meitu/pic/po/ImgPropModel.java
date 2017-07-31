package com.k4meitu.pic.po;
/*
 * `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(10) NOT NULL COMMENT '图组的ID',
  `imgId` int(15) NOT NULL COMMENT '图片的ID',
  `imgHeight` int(4) NOT NULL COMMENT '图片的高度',
  `imgWidth` int(4) NOT NULL COMMENT '图片的宽度',
  `imgSize` varchar(10) DEFAULT NULL COMMENT '图片文件大小',
  `imgTitle` varchar(20) DEFAULT '' COMMENT '图组的标题',
  `imgContent` varchar(150) DEFAULT '' COMMENT '图片的介绍文字',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据插入时间',
 * */

import java.sql.Timestamp;

public class ImgPropModel {
	private int id;
	private String groupId;
	private int imgId;
	private int imgHeight;
	private int imgWidth;
	private String imgSize;
	private String imgTitle;
	private String imgContent;
	private String imgName;
	private Timestamp date;
	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
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
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public String getImgSize() {
		return imgSize;
	}
	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}
	public String getImgTitle() {
		return imgTitle;
	}
	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}
	public String getImgContent() {
		return imgContent;
	}
	public void setImgContent(String imgContent) {
		this.imgContent = imgContent;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
