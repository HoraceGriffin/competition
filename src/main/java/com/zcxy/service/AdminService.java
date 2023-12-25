package com.zcxy.service;

import com.zcxy.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AdminService {

    /**
     * 登录相关
     */
    @Select("select * from tb_admin where username=#{username} and password=#{password}")
    Admin login(@Param("username") String username, @Param("password") String password);


    /**
     * 查看管理员个人信息
     */
    @Select("select * from tb_admin where id=#{id}")
    Admin getAdminById(@Param("id") Integer id);

    /**
     * 修改管理员个人信息
     */
    int updateAdmin(Admin admin);

    /**
     * 查看管理员信息
     */
    @Select("select * from tb_admin where username=#{username}")
    Admin getAdminByUsername(@Param("username") String username);

    /**
     * 修改管理员密码
     */
    @Update("update tb_admin set password=#{password} where id=#{id}")
    void updatePassword(Admin admin);

    /**
     * 上传头像信息
     */
    @Update("update tb_admin set image=#{image} where id=#{id}")
    void uploadPhoto(Admin admin);


}
