package com.zcxy.entity;

public class AdminSearch {
    private String username;
    private int sex;
    private int roleId;
    private int status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminSearch{" +
                "username='" + username + '\'' +
                ", sex=" + sex +
                ", roleId=" + roleId +
                ", status=" + status +
                '}';
    }
}
