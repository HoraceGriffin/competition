package com.zcxy.service.provider;


import com.zcxy.entity.CompetitionSearch;

public class CompetitionSQLProvider {
    public String getAllCompetitionSQL(CompetitionSearch search) {
        String sql = "select * from tb_competition where 1=1 ";
        if (search.getTitle() != null && search.getTitle() != "") {
            sql += "and title like '%${title}%' ";
        }

        if (search.getDeptId() != 0) {
            sql += "and deptId = #{deptId} ";
        }

        if (search.getPublisher() != null && search.getPublisher()!= "") {
            sql += "and publisher like '%${publisher}%' ";
        }

        if (search.getStatus() != 0) {
            sql += "and status = #{status} ";
        }

        if (search.getCreateTimeStart() != null && search.getCreateTimeEnd() != null
                && search.getCreateTimeStart() != "" && search.getCreateTimeEnd() != "") {
            sql += "and createTime between #{createTimeStart} and #{createTimeEnd} ";
        }
        if (search.getCreateTimeStart() != "" && search.getCreateTimeEnd() == "") {
            sql += "and createTime between #{createTimeStart} and curtime() ";
        }
        if (search.getCreateTimeStart() == "" && search.getCreateTimeEnd() != "") {
            sql += "and createTime between '1900-01-01 00:00:00' and #{createTimeEnd} ";
        }

        return sql;
    }

    public String deleteCompetitionByIdsSQL(int[] ids) {
        String sql ="delete from tb_competition where id in (";
        for (int i = 0; i < ids.length - 1; i++) {
            sql = sql + ids[i] + ",";
        }
        sql += ids[ids.length - 1] + ")";
        return sql;
    }
}
