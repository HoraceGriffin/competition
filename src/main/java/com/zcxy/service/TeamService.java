package com.zcxy.service;



import com.zcxy.entity.Team;
import com.zcxy.entity.TeamSearch;
import com.zcxy.service.provider.TeamSQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeamService {

    /**
     * 查询报名列表信息
     */
    @SelectProvider(type = TeamSQLProvider.class, method = "getTeamListSQL")
    List<Team> getTeamList(TeamSearch search);

    @Select("select * from tb_team where open_id = #{openId} order by id desc")
    List<Team> getOpenIdList(String openId);

    @Select("select * from tb_team where open_id=#{openId} and status = 2")
    List<Team> getStatusOpenId(String openId);

    /*查看审核报名信息*/
    @Select("select * from tb_team where id=#{id}")
    Team checkLookById(int id);

    @Select("select * from tb_team where  open_id =#{openId} and competitionTitle=#{title}")
    List<Team> getPlayer(String openId,String title);


    @Insert("insert into tb_team (teamName,projectName,members,adviser,content,status,note,competitionTitle,reason,open_id) values (#{teamName},#{projectName},#{members},#{adviser},#{content},#{status},#{note},#{competitionTitle},#{reason},#{openId})")
    void insert(Team team);

    /*提交审核通过信息*/
    @Update("update tb_team set status=2,reason=#{reason} where id=#{id}")
    void updateYesApply(Team team);

    /*提交审核不通过信息*/
    @Update("update tb_team set status=3,reason=#{reason} where id=#{id}")
    void updateNoApply(Team team);

    /*修改时获取报名信息*/
    @Select("select * from tb_team where id=#{id}")
    Team getTeamById(int id);

    /*修改报名信息*/
    @Update("update tb_team set teamName=#{teamName},projectName=#{projectName},members=#{members},adviser=#{adviser},content=#{content},note=#{note} where id=#{id}")
    void updateTeam(Team team);

    /**
     * 删除报名信息
     */
    @Delete("delete from tb_team where id = #{id}")
    void deleteTeamById(Integer id);

    /**
     * 批量删除比赛信息
     */
    @DeleteProvider(type = TeamSQLProvider.class, method = "deleteTeamByIdsSQL")
    void deleteTeamByIds(@Param("ids")int[] ids);

}
