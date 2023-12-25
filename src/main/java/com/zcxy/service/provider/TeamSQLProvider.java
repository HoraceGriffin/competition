package com.zcxy.service.provider;


import com.zcxy.entity.TeamSearch;

public class TeamSQLProvider {
    public String getTeamListSQL(TeamSearch search) {
        String sql = "select * from tb_team where 1=1 ";
        if (search.getTeamName() != null && search.getTeamName() != "") {
            sql += "and teamName like '%${teamName}%' ";
        }
        if (search.getProjectName() != null && search.getProjectName() != "") {
            sql += "and projectName like '%${projectName}%' ";
        }
        if (search.getAdviser() != null && search.getAdviser() != "") {
            sql += "and adviser like '%${adviser}%' ";
        }
        if (search.getCompetitionTitle() != null && search.getCompetitionTitle() != "") {
            sql += "and competitionTitle like '%${competitionTitle}%' ";
        }
        if (search.getStatus() != 0) {
            sql += "and status= #{status} ";
        }
        return sql;
    }

    public String deleteTeamByIdsSQL(int[] ids) {
        String sql = "delete from tb_team where id in (";
        for (int i = 0; i < ids.length - 1; i++) {
            sql = sql + ids[i] + ",";
        }
        sql += ids[ids.length - 1] + ")";
        return sql;
    }
}
