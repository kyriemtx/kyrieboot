package com.mtx.kyrieboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysProvince
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 11:41
 **/

@Data
public class SysProvince implements Serializable {

    private static final long serialVersionUID = 525560239051321634L;

    private int Id;

    private String provinceCode;

    private String provinceName;

    private String shortName;

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
