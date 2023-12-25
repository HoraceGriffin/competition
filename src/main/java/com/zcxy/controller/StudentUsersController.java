package com.zcxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcxy.entity.StudentBen;
import com.zcxy.entity.StudentUsers;
import com.zcxy.entity.StudentUsersSearch;
import com.zcxy.enums.ErrorCode;
import com.zcxy.service.StudentUsersService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResponseWrapper;
import com.zcxy.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/studentUsers")
public class StudentUsersController {

    @Autowired
    private StudentUsersService studentUsersService;

    @PostMapping("/loginMini")
    public ResponseWrapper<StudentUsers> getStatusIdAndPassword(@RequestBody StudentUsers studentUsers, HttpSession session){
        if (StringUtils.isEmpty(studentUsers.getPassword()) || studentUsers.getStudentId() == null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        StudentUsers studentUser = studentUsersService.selectStudentIdAndPassword(studentUsers.getStudentId(), MD5Util.MD5Encode(studentUsers.getPassword()));
        if (studentUser == null){
            return ResponseWrapper.fail(ErrorCode.DATA_NOT_EXIST);
        }
        session.setAttribute("admin",studentUser);
        Object admin = session.getAttribute("admin");
        System.out.println(admin);
        return ResponseWrapper.success(studentUser);
    }


    @GetMapping("/getStudentId")
    public ResponseWrapper<StudentUsers> getStudentId(Integer id){
        if (id == null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(studentUsersService.getStudentUsersById(id));
    }


    /**
     * 获取全部用户列表信息
     */
    @RequestMapping("/getStudentUserList")
    @ResponseBody
    public ResultUtil getStudentUserList(Integer page, Integer limit, StudentUsersSearch search) {
        PageHelper.startPage(page, limit);
        List<StudentUsers> studentUsers = studentUsersService.getStudentUserList(search);
        PageInfo<StudentUsers> pageInfo = new PageInfo<>(studentUsers);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }

    /*添加学生用户信息*/

    @RequestMapping("/insertStudentUsers")
    @ResponseBody
    public ResultUtil insertStudentUsers(StudentUsers studentUsers) {
        StudentUsers studentUsers1 = studentUsersService.selectStudentUsersByStudentId(studentUsers.getStudentId());
        if (null != studentUsers1) {
            return new ResultUtil(500, "管理员信息已存在，请重新填写！");
        }
        try {
            studentUsers.setPassword(MD5Util.MD5Encode(studentUsers.getPassword()));
            studentUsersService.insertStudentUsers(studentUsers);
            return ResultUtil.ok();
        } catch (Exception e) {
            return new ResultUtil(502, "网络错误，请检查网络！");
        }
    }
    /*查看学生信息是否存在*/

    @RequestMapping("/checkStudentUsersById/{studentId}")
    @ResponseBody
    public ResultUtil checkStudentUsersById(@PathVariable("studentId") int studentId) {
        StudentUsers studentUsers = studentUsersService.selectStudentUsersByStudentId(studentId);
        if (studentUsers != null) {
            return new ResultUtil(500, "学生信息已存在，请重新填写！");
        }
        return new ResultUtil(0);
    }



    /**
     * 获取学生用户信息
     */
    @RequestMapping("/editStudentUsers/{id}")
    @ResponseBody
    public ModelAndView editStudentUsers(@PathVariable("id") int id, HttpServletRequest request) {
        StudentUsers studentUsers = studentUsersService.getStudentUsersById(id);
        request.setAttribute("studentUsers", studentUsers);
        return new ModelAndView("superadmin/studentUsers/editStudentUsers");
    }

    /**
     * 获取学生用户信息（系统修改）
     */
    @RequestMapping("/modifyStudent/{id}")
    @ResponseBody
    public ModelAndView modifyStudent(@PathVariable("id") int id, HttpServletRequest request) {
        StudentUsers studentUsers = studentUsersService.getStuById(id);
        request.setAttribute("studentUsers", studentUsers);
        return new ModelAndView("superadmin/studentUsers/editStudentUsers");
    }
    /*修改学生用户信息
     */
    @RequestMapping("/editStudent")
    @ResponseBody
    public ResultUtil editStudent(StudentUsers studentUsers) {
        studentUsersService.editStudent(studentUsers);
        return ResultUtil.ok();
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetPass/{id}")
    @ResponseBody
    public ModelAndView resetPass(@PathVariable("id") int id, HttpServletRequest request) {
        StudentUsers studentUsers = studentUsersService.getStudentUsersById(id);
        request.setAttribute("studentUsers", studentUsers);
        return new ModelAndView("superadmin/studentUsers/resetPass");
    }

    /*重置密码(系统)
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResultUtil resetPassword(StudentUsers studentUsers) {
        studentUsers.setPassword(MD5Util.MD5Encode(studentUsers.getPassword()));
        studentUsersService.resetPassword(studentUsers);
        return ResultUtil.ok();
    }


    /**
     * 获取学生用户信息详情
     */
    @RequestMapping("/studentUsersDetails/{id}")
    @ResponseBody
    public ModelAndView studentUsersDetails(@PathVariable("id") int id, HttpServletRequest request) {
        StudentUsers studentUsers = studentUsersService.getStudentUsersById(id);
        request.setAttribute("studentUsers", studentUsers);
        return new ModelAndView("superadmin/studentUsers/studentUsersDetails");
    }

    /*修改学生用户信息
     */
    @RequestMapping("/updateStudentUsers")
    @ResponseBody
    public ResultUtil updateStudentUsers(@RequestBody StudentUsers studentUsers) {
        studentUsers.setPassword(MD5Util.MD5Encode(studentUsers.getPassword()));
        studentUsersService.updateStudentUsers(studentUsers);
        return ResultUtil.ok();
    }


    //修改密码（小程序）
    @PutMapping("/updatePass")
    public ResponseWrapper<Boolean> updatePassword(@RequestBody StudentBen studentBen){
        if (StringUtils.isEmpty(studentBen.getOldPassword()) || StringUtils.isEmpty(studentBen.getSurePassword()) || studentBen.getId() == null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        //判断密码是否正确
        StudentUsers studentUsersById = studentUsersService.getStudentUsersById(studentBen.getId());
        if (!studentUsersById.getPassword().equals(MD5Util.MD5Encode(studentBen.getOldPassword()))){
            return ResponseWrapper.fail(ErrorCode.DATA_NOT_EXIST);
        }
        StudentUsers studentUsers = new StudentUsers();
        studentUsers.setPassword(MD5Util.MD5Encode(studentBen.getSurePassword()));
        studentUsers.setId(studentBen.getId());
        studentUsersService.updateStudentPassword(studentUsers);
        return ResponseWrapper.success(Boolean.TRUE);
    }

    /* 删除学生用户信息*/

    @RequestMapping("/deleteStudentUsersById")
    @ResponseBody
    public ResultUtil deleteStudentUsersById(Integer id) {
        studentUsersService.deleteStudentUsersById(id);
        return ResultUtil.ok();
    }


    /* 批量删除学生用户信息*/

    @RequestMapping("/deleteStudentUsersByIds")
    @ResponseBody
    public ResultUtil deleteStudentUsersByIds(int[] ids) {
        studentUsersService.deleteStudentUsersByIds(ids);
        return ResultUtil.ok();
    }



    @RequestMapping("/studentUsersList")
    public ModelAndView toStudentUsersList() {
        return new ModelAndView("superadmin/studentUsers/studentUsersList");
    }

    @RequestMapping("/addStudentUsers")
    public ModelAndView toAddStudentUsers() {
        return new ModelAndView("superadmin/studentUsers/addStudentUsers");
    }

}
