package com.zcxy.controller;

import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;


@RestController
public class LoginApiController {

    @Resource private AdminService adminService;

    @PostMapping("/login")
    public ResultUtil login(@RequestBody Map<String, String> payload, HttpSession session)
            throws ParseException {
        Admin admin = adminService.login(payload.get("username"), MD5Util.MD5Encode(payload.get("password")));
        if (null == admin) {
            return ResultUtil.error(HttpStatus.BAD_REQUEST, "用户名或密码错误");
        }
        admin.setPassword(null);
        session.setAttribute("admin", admin);
        return ResultUtil.ok(admin);
    }

    @PostMapping("/logout")
    public ResultUtil logout(HttpSession session) {
        session.removeAttribute("admin");
        return ResultUtil.ok();
    }

}
