package com.zcxy.service.provider;


import com.zcxy.entity.AnnouncementSearch;

public class AnnouncementSQLProvider {
    public String getAllAnnouncementSQL(AnnouncementSearch search){
        String sql = "select * from tb_announcement where 1 = 1 ";
        if (search.getTitle() != null && search.getTitle() != "") {
            sql += "and title like '%${title}%' ";
        }

        if (search.getContent() != null && search.getContent() != "") {
            sql += "and content like '%${content}%' ";
        }
        if (search.getType() != 0) {
            sql += "and type = #{type} ";
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

    /*批量删除公告*/
    public String deleteAnnouncementByIdsSQL(int[] ids) {
        String sql ="delete from tb_announcement where id in (";
        for (int i = 0; i < ids.length - 1; i++) {
            sql = sql + ids[i] + ",";
        }
        sql += ids[ids.length - 1] + ")";
        return sql;
    }


}
