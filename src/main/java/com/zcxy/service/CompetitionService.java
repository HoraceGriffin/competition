package com.zcxy.service;

import com.zcxy.entity.Competition;
import com.zcxy.entity.CompetitionSearch;
import com.zcxy.service.provider.CompetitionSQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CompetitionService {

    /**
     * 获取比赛列表信息
     */
    @SelectProvider(type = CompetitionSQLProvider.class, method = "getAllCompetitionSQL")
    List<Competition> getAllCompetitionList(CompetitionSearch search);

    @Select("select * from tb_competition")
    List<Competition> queryCompetitions();


    /**
     * 获取比赛信息
     */
    @Select("select * from tb_competition where id=#{id}")
    Competition getCompetitionById(int id);

    /**
     * 修改比赛信息状态
     */
    /*@Update("update tb_competition set status = #{status} where id = #{id}")
    void updateCompetitionStatusById(@Param("id") int id, @Param("status") int status);*/

    /**
     * 修改信息
     */
    @Update("update tb_competition set title=#{title},deptId=#{deptId},publisher=#{publisher},email=#{email}," +
            "number=#{number},validTime=#{validTime},createTime=#{createTime},content=#{content},note=#{note}" +
            "where id=#{id}")
    void updateCompetition(Competition competition);

    /**
     * 查看比赛信息
     */
    @Select("select * from tb_competition where title = #{title}")
    Competition selectCompetitionByTitle(String title);

    /**
     * 添加比赛信息
     */
    @Insert("insert into tb_competition(title,deptId,publisher,email,number,validTime,createTime,content,note,activity_photo) " +
            "values(#{title},#{deptId},#{publisher},#{email},#{number},#{validTime},#{createTime},#{content},#{note},#{activity_photo})")
    void insertCompetition(Competition competition);




    /**
     * 删除比赛信息
     */
    @Delete("delete from tb_competition where id = #{id}")
    void deleteCompetitionById(Integer id);

    /**
     * 批量删除比赛信息
     */
    @DeleteProvider(type = CompetitionSQLProvider.class, method = "deleteCompetitionByIdsSQL")
    void deleteCompetitionByIds(@Param("ids")int[] ids);


    @Select("select * from tb_competition order by id desc")
    List<Competition> getCompetitions();

}
