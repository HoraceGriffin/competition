package com.zcxy.entity;

public class TeamSearch {
    private String teamName;
    private String projectName;
    private String adviser;
    private String competitionTitle;
    private int status;

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

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public String getCompetitionTitle() {
        return competitionTitle;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TeamSearch{" +
                "teamName='" + teamName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", adviser='" + adviser + '\'' +
                ", competitionTitle='" + competitionTitle + '\'' +
                ", status=" + status +
                '}';
    }
}
