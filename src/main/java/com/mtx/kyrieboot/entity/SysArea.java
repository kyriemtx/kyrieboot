package com.mtx.kyrieboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysArea
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:36
 **/
@Data
public class SysArea implements Serializable {

    private static final long serialVersionUID = 2689501496576027135L;

    private int id;

    private String areaCode;

    private String cityName;

    private String areaName;

    private String shortName;

    private String cityCode;

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
