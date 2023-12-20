package com.zcxy.entity;

public class StudentUsersSearch {
    private String studentId;
    private String name;
    private int sex;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "StudentUsersSearch{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
