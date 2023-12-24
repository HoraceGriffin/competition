package com.zcxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcxy.entity.Announcement;
import com.zcxy.entity.AnnouncementSearch;
import com.zcxy.enums.ErrorCode;
import com.zcxy.service.AnnouncementService;
import com.zcxy.utils.ResponseWrapper;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;


   /* 获取公告列表信息*/
    @RequestMapping("/getAllAnnouncementList")
    @ResponseBody
    public ResultUtil getResultUtil(Integer page, Integer limit, AnnouncementSearch search){
        PageHelper.startPage(page,limit);
        List<Announcement> announcements = announcementService.getAllAnnouncementList(search);
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcements);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }

    @GetMapping("/list")
    public ResultUtil listAnnouncement(Integer page, Integer pageSize, AnnouncementSearch search) {
        PageHelper.startPage(page, pageSize);
        List<Announcement> announcements = announcementService.getAllAnnouncementList(search);
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcements);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(HttpStatus.OK.value());
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }


    @GetMapping("/getNotice")
    public ResponseWrapper<List<Announcement>> getNotice(Integer type){
        if (type == null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (type == 0){
            return ResponseWrapper.success(announcementService.getThreeNotice());
        }
        return ResponseWrapper.success(announcementService.getNoticeAll(type));
    }

    @GetMapping("/getPlayers")
    public ResponseWrapper<List<Announcement>> getPlayerList(){
        return ResponseWrapper.success(announcementService.getNoticeAll(1));
    }

    /*获取公告信息*/
    @RequestMapping("/editAnnouncement/{id}")
    @ResponseBody
    public ModelAndView editAnnouncement(@PathVariable("id") int id, HttpServletRequest request){
        Announcement announcement = announcementService.getAnnouncementById(id);
        request.setAttribute("announcement",announcement);
        return new ModelAndView("admin/announcement/editAnnouncement");
    }


    /*查看公告信息*/
    @RequestMapping("/announcementDetails/{id}")
    @ResponseBody
    public ModelAndView announcementDetails(@PathVariable("id") int id, HttpServletRequest request){
        Announcement announcement = announcementService.getAnnouncementById(id);
        request.setAttribute("announcement",announcement);
        return new ModelAndView("admin/announcement/announcementDetails");
    }

    @GetMapping("/getNoticeDetail")
    public ResponseWrapper<Announcement> getNoticeDetail(Integer id){
        if (id == null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(announcementService.getAnnouncementById(id));
    }

    /*删除公告*/
    @RequestMapping("/deleteAnnouncementById")
    @ResponseBody
    public ResultUtil deleteCompetitionById(Integer id) {
        announcementService.deleteAnnouncementById(id);
        return ResultUtil.ok();
    }

    /**
     * 批量删除公告信息
     */
    @RequestMapping("/deleteAnnouncementByIds")
    @ResponseBody
    public ResultUtil deleteAnnouncementByIds(int[] ids) {
        announcementService.deleteAnnouncementByIds(ids);
        return ResultUtil.ok();
    }

    /**
     * 添加比赛信息
     */
    @RequestMapping("/insertAnnouncement")
    @ResponseBody
    public ResultUtil insertAnnouncement(Announcement announcement) {
        Announcement announcement1 = announcementService.selectAnnouncementByTitle(announcement.getTitle());
        if (null != announcement1) {
            return new ResultUtil(500, "此条公告已存在，请重新填写！");
        }
        try {
            announcement.setCreateTime(new Date());
            announcementService.insertAnnouncement(announcement);
            return ResultUtil.ok();
        } catch (Exception e) {
            return new ResultUtil(502, "网络错误，请检查网络！");
        }
    }

    /**
     * 修改比赛信息
     */
    @RequestMapping("/updateAnnouncement")
    @ResponseBody
    public ResultUtil updateAnnouncement(Announcement announcement) {
        announcementService.updateAnnouncement(announcement);
        return ResultUtil.ok();
    }

    @RequestMapping("/announcementList")
    public ModelAndView toAnnouncementList() {
        return new ModelAndView("admin/announcement/announcementList");
    }

    @RequestMapping("/addAnnouncement")
    public ModelAndView toAddAnnouncement() { return new ModelAndView("admin/announcement/addAnnouncement"); }
}
