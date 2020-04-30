package com.mtx.kyrieboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysRegion
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 11:46
 **/
@Data
@Builder
public class SysRegion implements Serializable {

    private static final long serialVersionUID = -5519559686361145178L;

    private int id;
    private String name;
    private int pid;
    private String sname;
    private int level;
    private String cityCode;
    private String postCode;
    private String merName;
    private String lng;
    private String lat;
    private String pinyin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    private String status;

    public SysRegion(int id, String name, int pid, String sname, int level, String cityCode,
                     String postCode, String merName, String lng, String lat,
                     String pinyin, Date gmtCreate, Date gmtModified, String status) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.sname = sname;
        this.level = level;
        this.cityCode = cityCode;
        this.postCode = postCode;
        this.merName = merName;
        this.lng = lng;
        this.lat = lat;
        this.pinyin = pinyin;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.status = status;
    }
}
