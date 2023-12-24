package com.zcxy.controller;

import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.MD5Util;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private AdminService adminService;

    /**
     * 获取个人信息
     */
    @RequestMapping("/personalDate")
    public ModelAndView personalDate(HttpServletRequest request,HttpSession session) {
        Admin pAdmin = (Admin) session.getAttribute("admin");
        Admin admin = adminService.getAdminById(pAdmin.getId());
        request.setAttribute("personal", admin);
        return new ModelAndView("admin/person/personalInfo");
    }

    /**
     * 修改个人信息
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public ResultUtil updateAdmin(Admin admin) {
        try {
            adminService.updateAdmin(admin);
            return ResultUtil.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

    /**
     * 上传头像信息
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ResultUtil uploadPhoto(MultipartFile file, HttpSession session, HttpServletRequest request) {
        Admin admin = (Admin) session.getAttribute("admin");
        //判断上传文件是否存在
        if (!file.isEmpty()) {
            //获取上传文件的原始名
            String fileName = file.getOriginalFilename();
            // 截取文件类型
            int indexdot = fileName.indexOf(".");
            String suffix = fileName.substring(indexdot);
            //设置上传文件的目录
            //String dirPath = request.getRealPath("/upload/");
            String dirPath ="/Users/guoshunsir/ruoyi/upload";
            File filePath = new File(dirPath);
            //如果目录不存在，则创建目录
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            //使用UUID重新命名上传文件名称（上传人_uuid)
            String newFilename = admin.getUsername() + "_" + UUID.randomUUID() + suffix;
            try {
                file.transferTo(new File(dirPath + newFilename));
                admin.setImage("/upload/"+newFilename);
                adminService.uploadPhoto(admin);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error();
            }
        }
        List<Object> list=new ArrayList<>();
        list.add(admin.getImage());
        return ResultUtil.ok(list);
    }

    /**
     * 跳转修改登录密码页面
     */
    @RequestMapping("/changePassword")
    public ModelAndView toChangePwd(HttpServletRequest request,HttpSession session) {
        Admin pAdmin = (Admin) session.getAttribute("admin");
        Admin admin = adminService.getAdminById(pAdmin.getId());
        request.setAttribute("personal", admin);
        return new ModelAndView("admin/person/changePassword");
    }

    /**
     * 修改登录密码
     */
    @RequestMapping("/changeAdminPassword")
    @ResponseBody
    public ResultUtil changeAdminPassword(String oldPassword, String newPassword1, String username) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            if (admin.getPassword().equals(MD5Util.MD5Encode(oldPassword))) {
                admin.setPassword(MD5Util.MD5Encode(newPassword1));
                adminService.updatePassword(admin);
                return ResultUtil.ok();
            } else {
                return new ResultUtil(501, "旧密码错误，请重新填写！");
            }
        }
        return new ResultUtil(500, "请求错误！");
    }


}
