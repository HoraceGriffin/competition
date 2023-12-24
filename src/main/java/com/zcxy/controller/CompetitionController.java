package com.zcxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcxy.entity.Competition;
import com.zcxy.entity.CompetitionSearch;
import com.zcxy.enums.ErrorCode;
import com.zcxy.service.CompetitionService;
import com.zcxy.utils.ResponseWrapper;
import com.zcxy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * 获取比赛列表信息
     */
    @RequestMapping("/getAllCompetitionList")
    @ResponseBody
    public ResultUtil getResultUtil(Integer page, Integer limit, CompetitionSearch search) {
        PageHelper.startPage(page, limit);
        List<Competition> competitions = competitionService.getAllCompetitionList(search);
        PageInfo<Competition> pageInfo = new PageInfo<>(competitions);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }

    @ResponseBody
    @GetMapping("/list")
    public ResultUtil listCompetitions(Integer page, Integer limit, CompetitionSearch search) {
        PageHelper.startPage(page, limit);
        List<Competition> competitions = competitionService.getAllCompetitionList(search);
        PageInfo<Competition> pageInfo = new PageInfo<>(competitions);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(HttpStatus.OK.value());
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }


    @GetMapping("/getCompetitions")
    public ResponseWrapper<List<Competition>> getCompetitions(){
       return ResponseWrapper.success( competitionService.getCompetitions());
    }

    /**
     * 获取比赛信息
     */
    @RequestMapping("/editCompetition/{id}")
    @ResponseBody
    public ModelAndView editCompetition(@PathVariable("id") int id, HttpServletRequest request) {
        Competition competition = competitionService.getCompetitionById(id);
        request.setAttribute("competition", competition);
        return new ModelAndView("admin/competition/editCompetition");
    }

    @GetMapping("/getCompetitionDetail")
    public ResponseWrapper<Competition>  getCompetitionDetail(Integer id){
        if (id == null) {
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(competitionService.getCompetitionById(id));
    }

    /**
     * 查看比赛信息详情
     */
    @RequestMapping("/competitionDetails/{id}")
    @ResponseBody
    public ModelAndView competitionDetails(@PathVariable("id") int id, HttpServletRequest request) {
        Competition competition = competitionService.getCompetitionById(id);
        request.setAttribute("competition", competition);
        return new ModelAndView("admin/competition/competitionDetails");
    }

    /**
     * 删除比赛信息
     */
    @RequestMapping("/deleteCompetitionById")
    @ResponseBody
    public ResultUtil deleteCompetitionById(Integer id) {
        competitionService.deleteCompetitionById(id);
        return ResultUtil.ok();
    }

    /**
     * 批量删除比赛信息
     */
    @RequestMapping("/deleteCompetitionByIds")
    @ResponseBody
    public ResultUtil deleteCompetitionByIds(int[] ids) {
        competitionService.deleteCompetitionByIds(ids);
        return ResultUtil.ok();
    }

    /**
     * 添加比赛信息
     */
    @RequestMapping("/insertCompetition")
    @ResponseBody
    public ResultUtil insertCompetition(Competition competition) {
        Competition competition1 = competitionService.selectCompetitionByTitle(competition.getTitle());
        if (null != competition1) {
            return new ResultUtil(500, "此条比赛已存在，请重新填写！");
        }
        try {
            competition.setCreateTime(new Date());
            competitionService.insertCompetition(competition);
            return ResultUtil.ok();
        } catch (Exception e) {
            return new ResultUtil(502, "网络错误，请检查网络！");
        }
    }



    /**
     * 修改信息状态
     */
 /*   @RequestMapping("/updateCompetitionStatusById")
    @ResponseBody
    public ResultUtil updateCompetitionStatusById(int id, int status) {
        competitionService.updateCompetitionStatusById(id,status);
        return ResultUtil.ok();
    }*/

    /**
     * 修改比赛信息
     */
    @RequestMapping("/updateCompetition")
    @ResponseBody
    public ResultUtil updateCompetition(Competition competition) {
       /* if (null != competition.getPassword()) {
            competition.setPassword(MD5Util.MD5Encode(competition.getPassword()));
        }*/
        competitionService.updateCompetition(competition);
        return ResultUtil.ok();
    }

    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ResultUtil uploadPhoto(MultipartFile file, String photoName) {
        //判断上传文件是否存在
        if (!file.isEmpty()) {
            //设置上传文件的目录
            String dirPath = "/Users/guoshunsir/ruoyi/upload";
            File filePath = new File(dirPath);
            //如果目录不存在，则创建目录
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            try {
                file.transferTo(new File(dirPath + photoName));
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error();
            }
        }
        return ResultUtil.ok();
    }

    @RequestMapping("/competitionList")
    public ModelAndView toCompetitionList() {
        return new ModelAndView("admin/competition/competitionList");
    }

    @RequestMapping("/addCompetition")
    public ModelAndView toAddCompetition() {
        return new ModelAndView("admin/competition/addCompetition");
    }
}
