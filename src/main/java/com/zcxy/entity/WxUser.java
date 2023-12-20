package com.zcxy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author guoshun
 * @date   2021/10/27
 */
public class WxUser implements Serializable {
    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String avatar;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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