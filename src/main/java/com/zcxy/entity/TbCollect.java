package com.zcxy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author guoshun
 * @date   2021/10/30
 */
public class TbCollect implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private Integer competitionId;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModify;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}