package com.mtx.kyrieboot.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysUser
 * @Description 用户实体类
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:00
 **/
@Data
@Builder
public class SysUser implements Serializable {

    private static final long serialVersionUID = -3747386723206797631L;

    private String id;

    private String name;

    private String password;

    private String nickName;

    private String sex;

    private String mobile;

    private String email;

    private String birthday;

    private String hobby;

    private String liveAddress;

    private Date createTime;

    private Date updateTime;

    public SysUser(){

    }

    public SysUser(String id, String name, String password, String nickName, String sex, String mobile,
                   String email, String birthday, String hobby, String liveAddress, Date createTime, Date updateTime){
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
        this.liveAddress = liveAddress;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
