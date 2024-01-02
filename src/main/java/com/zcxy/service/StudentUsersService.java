package com.zcxy.service;


import com.zcxy.entity.StudentUsers;
import com.zcxy.entity.StudentUsersSearch;
import com.zcxy.service.provider.StudentUsersSQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StudentUsersService {

    /*查询管理员列表信息*/
    @SelectProvider(type = StudentUsersSQLProvider.class,method = "getStudentUsersListSQL")
    List<StudentUsers> getStudentUserList(StudentUsersSearch search);

    /*查看学生学号*/
    @Select("select studentId from tb_students where studentId=#{studentId}")
    StudentUsers selectStudentUsersByStudentId(int studentId);


    @Select("select *  from tb_students where studentId=#{studentId} and password = #{password}")
    StudentUsers selectStudentIdAndPassword(Integer studentId,String password);

    /*添加学生用户信息*/
    @Insert("insert into tb_students(studentId,password,name,sex) values(#{studentId},#{password},#{name},#{sex})")
    void insertStudentUsers(StudentUsers studentUsers);

    /*修改学生用户信息(获取信息再修改)*/
    @Select("select * from tb_students where id=#{id}")
    StudentUsers getStudentUsersById(int id);

    /*修改学生用户信息(获取信息再修改)*/
    @Select("select * from tb_students where id=#{id}")
    StudentUsers getStuById(int id);

    /*修改学生用户信息*/
    @Update("update tb_students set studentId=#{studentId},password=#{password},name=#{name},sex=#{sex},phone=#{phone},email=#{email},photo=#{photo} where id=#{id}")
    void updateStudentUsers(StudentUsers studentUsers);


    /*修改学生用户信息(系统)*/
    @Update("update tb_students set studentId=#{studentId},name=#{name},sex=#{sex},phone=#{phone},email=#{email} where id = #{id}")
    void editStudent(StudentUsers studentUsers);

    /*修改密码*/
    @Update("update tb_students set password=#{password} where id = #{id}")
    void resetPassword(StudentUsers studentUsers);

    /*修改密码(小程序)*/
    @Update("update tb_students set password=#{password} where id=#{id}")
    void updateStudentPassword(StudentUsers studentUsers);

    /*删除学生用户信息*/
    @Delete("delete from tb_students where id = #{id}")
    void deleteStudentUsersById(Integer id);

    /*批量删除学生用户信息*/
    @DeleteProvider(type = StudentUsersSQLProvider.class,method = "deleteStudentUsersByIdsSQL")
    void deleteStudentUsersByIds(@Param("ids")int[] ids);
}
