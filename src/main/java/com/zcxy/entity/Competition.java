
package com.zcxy.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Competition {

	private int id;

	private String title;

	private int deptId;

	private String publisher;

	private String email;

	private int number;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validTime;

	private int status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	private String content;

	private String note;

	private String activity_photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getActivity_photo() {
		return activity_photo;
	}

	public void setActivity_photo(String activity_photo) {
		this.activity_photo = activity_photo;
	}

	@Override
	public String toString() {
		return "Competition{" +
				"id=" + id +
				", title='" + title + '\'' +
				", deptId=" + deptId +
				", publisher='" + publisher + '\'' +
				", email='" + email + '\'' +
				", number=" + number +
				", validTime=" + validTime +
				", status=" + status +
				", createTime=" + createTime +
				", content='" + content + '\'' +
				", note='" + note + '\'' +
				", activity_photo='" + activity_photo + '\'' +
				'}';
	}
}
