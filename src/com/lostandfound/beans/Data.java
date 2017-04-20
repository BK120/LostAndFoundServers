package com.lostandfound.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * 数据类
 * 
 * @author lee
 *
 */
public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 事件ID
	private String type;// 事件类型
	private String name;// 事关物名
	private String detail;// 物品描述
	private String remark;// 备注
	private String place;// 事发地点
	private Date incidentDate1;// 事发日期始
	private String incidentTime1;// 事发时间始
	private Date incidentDate2;// 事发日期终
	private String incidentTime2;// 事发时间终
	private Date publishDate;// 发布日期
	private String publishTime;// 发布时间
	private String picName;// 图片名
	private String isFinish;// 是否结束
	private String userPhone;// 用户电话，即帐号

	public Data() {
	}

	public Data(Integer id, String type, String name, String detail,
			String remark, String place, Date incidentDate1,
			String incidentTime1, Date incidentDate2, String incidentTime2,
			Date publishDate, String publishTime, String picName,
			String isFinish, String userPhone) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.detail = detail;
		this.remark = remark;
		this.place = place;
		this.incidentDate1 = incidentDate1;
		this.incidentTime1 = incidentTime1;
		this.incidentDate2 = incidentDate2;
		this.incidentTime2 = incidentTime2;
		this.publishDate = publishDate;
		this.publishTime = publishTime;
		this.picName = picName;
		this.isFinish = isFinish;
		this.userPhone = userPhone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getIncidentDate1() {
		return incidentDate1;
	}

	public void setIncidentDate1(Date incidentDate1) {
		this.incidentDate1 = incidentDate1;
	}

	public String getIncidentTime1() {
		return incidentTime1;
	}

	public void setIncidentTime1(String incidentTime1) {
		this.incidentTime1 = incidentTime1;
	}

	public Date getIncidentDate2() {
		return incidentDate2;
	}

	public void setIncidentDate2(Date incidentDate2) {
		this.incidentDate2 = incidentDate2;
	}

	public String getIncidentTime2() {
		return incidentTime2;
	}

	public void setIncidentTime2(String incidentTime2) {
		this.incidentTime2 = incidentTime2;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", type=" + type + ", name=" + name
				+ ", detail=" + detail + ", remark=" + remark + ", place="
				+ place + ", incidentDate1=" + incidentDate1
				+ ", incidentTime1=" + incidentTime1 + ", incidentDate2="
				+ incidentDate2 + ", incidentTime2=" + incidentTime2
				+ ", publishDate=" + publishDate + ", publishTime="
				+ publishTime + ", picName=" + picName + ", isFinish="
				+ isFinish + ", userPhone=" + userPhone + "]";
	}
}
