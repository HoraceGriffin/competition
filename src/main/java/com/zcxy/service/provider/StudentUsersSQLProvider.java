package com.zcxy.service.provider;


import com.zcxy.entity.StudentUsersSearch;

public class StudentUsersSQLProvider {

    public String getStudentUsersListSQL(StudentUsersSearch search) {
        String sql = "select * from tb_students where 1=1 ";
        if (search.getStudentId() != null && search.getStudentId() != "") {
            sql += "and studentId like '%${studentId}%' ";
        }
        if (search.getName() != null && search.getName() != "") {
            sql += "and name like '%${name}%' ";
        }

        if (search.getSex() != 0) {
            sql += "and sex= #{sex} ";
        }

        return sql;
    }

    public String deleteStudentUsersByIdsSQL(int[] ids) {
        String sql = "delete from tb_students where id in (";
        for (int i = 0; i < ids.length - 1; i++) {
            sql = sql + ids[i] + ",";
        }
        sql += ids[ids.length - 1] + ")";
        return sql;
    }



}
