package com.zcxy.entity;

public class StudentBen {
    private Integer id;

    private String password;

    private String oldPassword;

    private String surePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getSurePassword() {
        return surePassword;
    }

    public void setSurePassword(String surePassword) {
        this.surePassword = surePassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentBen{" +
                "password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", surePassword='" + surePassword + '\'' +
                '}';
    }
}
