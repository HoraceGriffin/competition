package com.zcxy.service;

import com.zcxy.entity.Admin;
import com.zcxy.entity.AdminSearch;
import com.zcxy.service.provider.AdminSQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminUsersService {

    /*查询管理员列表信息*/
    @SelectProvider(type = AdminSQLProvider.class,method = "getAdminListSQL")
    List<Admin> getAdminList(AdminSearch search);

    /*修改管理员信息*/
    @Select("select * from tb_admin where id=#{id}")
    Admin getAdminById(int id);

    /*查看管理员用户名*/
    @Select("select username from tb_admin where username=#{username}")
    Admin selectAdminByUserName(String username);

    /*添加管理员信息*/
    @Insert("insert into tb_admin(username,password,nickname,sex,roleId) values(#{username},#{password},#{nickname},#{sex},2)")
    void insertAdmin(Admin admin);

    /*修改管理员信息*/
    @Update("update tb_admin set username=#{username},nickname=#{nickname},sex=#{sex},phone=#{phone},email=#{email} where id=#{id}")
    void updateAdmin(Admin admin);

    /*重置密码*/
    @Update("update tb_admin set password=#{password} where id = #{id}")
    void updatePass(Admin admin);

    /*删除管理员信息*/
    @Delete("delete from tb_admin where id = #{id}")
    void deleteAdminById(Integer id);

    /*批量删除管理员信息*/
    @DeleteProvider(type = AdminSQLProvider.class,method = "deleteAdminByIdsSQL")
    void deleteAdminByIds(@Param("ids")int[] ids);

}
