package com.zcxy.controller;

import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * @author : Horace Leoi
 * @date : 2023/12/19
 * @remark :
 */
@RestController
public class LoginApiController {

    @Resource private AdminService adminService;

    @RequestMapping("/login")
    public ResultUtil login(String username, String password, HttpSession session)
            throws ParseException {
        Admin admin = adminService.login(username, MD5Util.MD5Encode(password));
        if (null == admin) {
            return ResultUtil.error(HttpStatus.BAD_REQUEST, "账号不存在！");
        }
        session.setAttribute("admin", admin);
        return ResultUtil.ok(admin);
    }

    @RequestMapping("/logout")
    public ResultUtil logout(HttpSession session) {
        session.removeAttribute("admin");
        return ResultUtil.ok();
    }

}
