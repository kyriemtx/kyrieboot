package com.mtx.kyrieboot.entity;
import lombok.Builder;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysRole
 * @Description 角色实体类
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:01
 **/
@Data
@Builder
public class SysRole implements Serializable {

    private static final long serialVersionUID = -9017496283614171917L;

    private String id;

    private String authority;

    private String name;

    private Date createTime;

    public SysRole(){

    }

    public SysRole(String id, String name, String authority, Date createTime){
        this.id = id;
        this.name = name;
        this.authority = authority;
        this.createTime = createTime;
    }
}
