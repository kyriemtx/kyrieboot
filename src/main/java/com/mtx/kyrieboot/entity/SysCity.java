package com.mtx.kyrieboot.entity;
import	java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import	java.io.Serializable;

/**
 * @ClassName SysCity
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 12:50
 **/

@Data
public class SysCity implements Serializable{

    private static final long serialVersionUID = -9078512120479522637L;

    private int Id;

    private String cityCode;

    private String cityName;

    private String shortName;

    private String provinceCode;

    private String lang;

    private String lat;

    private int sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private String memo;

    private String dataState;
}
