package com.zcxy.service.provider;


import com.zcxy.entity.AdminSearch;

public class AdminSQLProvider {

    public String getAdminListSQL(AdminSearch search) {
        String sql = "select * from tb_admin where 1=1 ";
        if (search.getUsername() != null && search.getUsername() != "") {
            sql += "and username like '%${username}%' ";
        }
        if (search.getSex() != 0) {
            sql += "and sex= #{sex} ";
        }
        if (search.getRoleId() != 0) {
            sql += "and roleId= #{roleId} ";
        }
        if (search.getStatus() != 0) {
            sql += "and status= #{status} ";
        }
        return sql;
    }

    public String deleteAdminByIdsSQL(int[] ids) {
        String sql = "delete from tb_admin where id in (";
        for (int i = 0; i < ids.length - 1; i++) {
            sql = sql + ids[i] + ",";
        }
        sql += ids[ids.length - 1] + ")";
        return sql;
    }



}
