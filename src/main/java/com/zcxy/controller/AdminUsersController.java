package com.zcxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcxy.entity.Admin;
import com.zcxy.entity.AdminSearch;
import com.zcxy.service.AdminUsersService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

;

@RestController
@RequestMapping("/adminUsers")
public class AdminUsersController {

    @Autowired
    private AdminUsersService adminUsersService;

    /**
     * 获取全部管理员列表信息
     */
    @RequestMapping("/getAdminList")
    @ResponseBody
    public ResultUtil getAdminList(Integer page, Integer limit, AdminSearch search) {
        PageHelper.startPage(page, limit);
        List<Admin> adminUsers = adminUsersService.getAdminList(search);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminUsers);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }

    /**
     * 获取管理员信息
     */
    @RequestMapping("/editAdmin/{id}")
    @ResponseBody
    public ModelAndView editAdmin(@PathVariable("id") int id, HttpServletRequest request) {
        Admin admin = adminUsersService.getAdminById(id);
        request.setAttribute("admin", admin);
        return new ModelAndView("superadmin/adminUsers/editAdmin");
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetPass/{id}")
    @ResponseBody
    public ModelAndView resetPass(@PathVariable("id") int id, HttpServletRequest request) {
        Admin admin = adminUsersService.getAdminById(id);
        request.setAttribute("admin", admin);
        return new ModelAndView("superadmin/adminUsers/resetPass");
    }
    /*修改管理员信息
     */
    @RequestMapping("/updatePass")
    @ResponseBody
    public ResultUtil updatePass(Admin admin) {
        admin.setPassword(MD5Util.MD5Encode(admin.getPassword()));
        adminUsersService.updatePass(admin);
        return ResultUtil.ok();
    }


    /**
     * 获取管理员信息详情
     */
    @RequestMapping("/adminUsersDetails/{id}")
    @ResponseBody
    public ModelAndView adminUsersDetails(@PathVariable("id") int id, HttpServletRequest request) {
        Admin admin = adminUsersService.getAdminById(id);
        request.setAttribute("admin", admin);
        return new ModelAndView("superadmin/adminUsers/adminUsersDetails");
    }


    /*修改管理员信息
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public ResultUtil updateAdmin(Admin admin) {
        /* admin.setPassword(MD5Util.MD5Encode(admin.getPassword()));*/
        adminUsersService.updateAdmin(admin);
        return ResultUtil.ok();
    }

    /* 删除管理员信息*/

    @RequestMapping("/deleteAdminById")
    @ResponseBody
    public ResultUtil deleteAdminById(Integer id) {
        adminUsersService.deleteAdminById(id);
        return ResultUtil.ok();
    }


    /* 批量删除管理员信息*/

    @RequestMapping("/deleteAdminByIds")
    @ResponseBody
    public ResultUtil deleteAdminByIds(int[] ids) {
        adminUsersService.deleteAdminByIds(ids);
        return ResultUtil.ok();
    }

    /*添加管理员信息*/

    @RequestMapping("/insertAdmin")
    @ResponseBody
    public ResultUtil insertAdmin(Admin admin) {
        Admin admin1 = adminUsersService.selectAdminByUserName(admin.getUsername().toUpperCase());
        if (null != admin1) {
            return new ResultUtil(500, "管理员信息已存在，请重新填写！");
        }
        try {
            admin.setPassword(MD5Util.MD5Encode(admin.getPassword()));
            adminUsersService.insertAdmin(admin);
            return ResultUtil.ok();
        } catch (Exception e) {
            return new ResultUtil(502, "网络错误，请检查网络！");
        }
    }


    /*查看管理员信息是否存在*/

    @RequestMapping("/checkAdminByName/{username}")
    @ResponseBody
    public ResultUtil checkAdminByName(@PathVariable("username") String username) {
        Admin admin = adminUsersService.selectAdminByUserName(username.toUpperCase());
        if (admin != null) {
            return new ResultUtil(500, "管理员信息已存在，请重新填写！");
        }
        return new ResultUtil(0);
    }

    @RequestMapping("/adminList")
    public ModelAndView toAdminList() {
        return new ModelAndView("superadmin/adminUsers//adminUsersList");
    }

    @RequestMapping("/addAdmin")
    public ModelAndView toAddAdmin() {
        return new ModelAndView("superadmin/adminUsers/addAdmin");
    }

}
