package com.zcxy.controller;

import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource private AdminService adminService;

    @GetMapping
    public ResultUtil getProfile(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        admin = adminService.getAdminById(admin.getId());
        return ResultUtil.ok(admin);
    }

    @PutMapping
    public ResultUtil updateProfile(HttpSession session, @RequestBody Admin admin) {
        Admin sessionAdmin = (Admin) session.getAttribute("admin");
        admin.setId(sessionAdmin.getId());
        admin.setUsername(sessionAdmin.getUsername());
        adminService.updateAdmin(admin);
        return ResultUtil.ok();
    }

    @PostMapping("/password")
    public ResultUtil updatePassword(HttpSession session, String oldPassword, String newPassword) {
        Admin admin = (Admin) session.getAttribute("admin");
        admin = adminService.getAdminByUsername(admin.getUsername());
        if (ObjectUtils.isEmpty(admin)) {
            return new ResultUtil(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户信息错误");
        }

        if (admin.getPassword().equals(MD5Util.MD5Encode(oldPassword))) {
            admin.setPassword(MD5Util.MD5Encode(newPassword));
            adminService.updatePassword(admin);
            return ResultUtil.ok();
        }

        return new ResultUtil(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求错误！");
    }

}
