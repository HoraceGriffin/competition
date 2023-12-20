package com.zcxy.entity;

public class Team {

    private int id;
    private String teamName;
    private String projectName;
    private String members;
    private String adviser;
    private String content;
    private int status;
    private String note;
    private String competitionTitle;
    private String reason;
    private String openId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCompetitionTitle() {
        return competitionTitle;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", members='" + members + '\'' +
                ", adviser='" + adviser + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", competitionTitle='" + competitionTitle + '\'' +
                ", reason='" + reason + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
