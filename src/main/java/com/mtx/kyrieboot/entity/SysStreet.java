package com.mtx.kyrieboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysStreet
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:22
 **/
@Data
public class SysStreet implements Serializable{

    private static final long serialVersionUID = -4866032673467922302L;

    private int id;

    private String streetCode;

    private String streetName;

    private String shortName;

    private String areaCode;

    private String areaName;

    private String lng;

    private String lat;

    private int sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private String memo;

    private String dataState;
}