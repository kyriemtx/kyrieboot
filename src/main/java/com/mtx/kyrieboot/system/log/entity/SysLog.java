package com.mtx.kyrieboot.system.log.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysLog
 * @Description  系统日志实体类
 * @Author tengxiao.ma
 * @Date 2020/4/21 14:58
 **/
@Data
@Builder
public class SysLog implements Serializable {

    private static final long serialVersionUID = 5998568611551230951L;


    private String id;

    private String username;

    private String ipAddress;

    private String ipSource;

    private String message;

    private String browserName;

    private String systemName;

    private Date createDate;

}
