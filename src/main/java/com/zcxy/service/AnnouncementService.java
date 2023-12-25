package com.zcxy.service;

import com.zcxy.entity.Announcement;
import com.zcxy.entity.AnnouncementSearch;
import com.zcxy.service.provider.AnnouncementSQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Mapper
public interface AnnouncementService {
/*
    获取公告列表信息
    */
    @SelectProvider(type = AnnouncementSQLProvider.class,method = "getAllAnnouncementSQL")
    List<Announcement> getAllAnnouncementList(AnnouncementSearch search);

    @Select("select * from tb_announcement where type = 1 order by id desc  limit 3")
    List<Announcement> getThreeNotice();

    @Select("select * from tb_announcement where type = #{type} order by id desc")
    List<Announcement> getNoticeAll(Integer type);


    //查询所有比赛信息
    @Select("select * from tb_announcement limit #{start},${size}")
    List<Announcement> findAllThemes(HashMap<String,Object> map);
    //分页总数
    @Select("select COUNT(*) from tb_announcement")
    int selectCount();

    /*获取公告信息*/
    @Select("select * from tb_announcement where id = #{id}")
    Announcement getAnnouncementById(int id);

    /*修改公告信息*/
    @Update("update tb_announcement set title=#{title},content=#{content},createTime = #{createTime} where id = #{id}")
    void updateAnnouncement(Announcement announcement);

    /*添加公告信息*/
    @Insert("insert into tb_announcement(title,content,createTime,type) values(#{title},#{content},#{createTime},#{type})")
    void insertAnnouncement(Announcement announcement);

    /**
     * 查看比赛信息
     */
    @Select("select * from tb_announcement where title = #{title}")
    Announcement selectAnnouncementByTitle(String title);


    /*删除公告*/
    @Delete("delete from tb_announcement where id = #{id}")
    void deleteAnnouncementById(Integer id);

    /*批量删除公告*/

    @DeleteProvider(type = AnnouncementSQLProvider.class,method = "deleteAnnouncementByIdsSQL")
    void deleteAnnouncementByIds(@Param("ids")int[] ids);

}




