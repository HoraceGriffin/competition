package com.zcxy.controller;



import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;


@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultUtil login(String username, String password, HttpServletRequest request, HttpSession session)
            throws ParseException {
        Admin admin1 = adminService.getAdminByUsername(username);
        if (null != admin1) {
            Admin admin = adminService.login(username, MD5Util.MD5Encode(password));
            if (null != admin) {
                session.setAttribute("admin", admin);
                return ResultUtil.ok(admin);
            } else {
                return ResultUtil.error(HttpStatus.BAD_REQUEST, "密码错误！");
            }
        } else {
            return ResultUtil.error(HttpStatus.BAD_REQUEST, "账号不存在！");
        }
    }

    @RequestMapping("/loginOut")
    public ModelAndView toLogout(HttpServletRequest request,HttpSession session) throws ParseException {

        return new ModelAndView("login");
    }

    /************************************页面跳转******************************/

    @RequestMapping("/content")
    public ModelAndView Main() {
        return new ModelAndView("admin/main");
    }

    @RequestMapping("/admin")
    public ModelAndView toAdminIndex() {
        return new ModelAndView("admin/index");
    }

    @RequestMapping("/supperAdmin")
    public ModelAndView supperAdmin() {
        return new ModelAndView("superadmin/index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
