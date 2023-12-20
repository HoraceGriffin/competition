package com.zcxy.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FileUtil;
import com.zcxy.entity.Admin;
import com.zcxy.service.AdminService;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author : Horace Leoi
 * @date : 2023/12/19
 * @remark :
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource private AdminService adminService;
    @Value("${file.upload.path}") private String FILE_UPLOAD_PATH;

    @RequestMapping("/avatar")
    public ResultUtil uploadPhoto(MultipartFile file, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        //判断上传文件是否存在
        if (ObjectUtils.isEmpty(file) && file.getOriginalFilename() != null) {
           return new ResultUtil(HttpStatus.BAD_REQUEST.value(), "上传文件不能为空)");
        }
        //获取上传文件的原始名
        String fileName = file.getOriginalFilename();
        // 截取文件类型
        int indexDot = fileName.indexOf(".");
        String suffix = fileName.substring(indexDot);
        String dirPath = FILE_UPLOAD_PATH + "/avatar/";
        File filePath = new File(dirPath);
        //如果目录不存在，则创建目录
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        //使用UUID重新命名上传文件名称（上传人_uuid)
        String newFilename = admin.getUsername() + "_" + UUID.randomUUID() + suffix;
        try {
            file.transferTo(new File(dirPath + newFilename));
            admin.setImage("http://localhost:8081/file/avatar/" + newFilename);
            adminService.uploadPhoto(admin);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
        return ResultUtil.ok(admin.getImage());
    }

    @GetMapping("/avatar/{file_name}")
    public ResultUtil getAvatar(@PathVariable("file_name") String filename) {
        byte[] bytes = FileUtil.readBytes(FILE_UPLOAD_PATH + "/avatar/" + filename);
        String encoded = Base64.getEncoder().encodeToString(bytes);

        return ResultUtil.ok((Object) encoded);
    }
}
