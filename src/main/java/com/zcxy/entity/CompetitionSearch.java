package com.zcxy.entity;

public class CompetitionSearch {

	private String title;

	private int deptId;

	private String publisher;

	private int status;

	private String createTimeStart;

	private String createTimeEnd;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	@Override
	public String toString() {
		return "CompetitionSearch{" +
				"title='" + title + '\'' +
				", deptId=" + deptId +
				", publisher=" + publisher +
				", status=" + status +
				", createTimeStart='" + createTimeStart + '\'' +
				", createTimeEnd='" + createTimeEnd + '\'' +
				'}';
	}
}
