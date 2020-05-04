package com.mtx.kyrieboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtx.kyrieboot.system.areamanager.region.entity.SysRegion;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysRegionListVO
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 11:51
 **/
@Data
public class SysRegionListVO {
    /** 区域主键 **/
    private int id;
    /** 区域名称 **/
    private String name;
    /** 区域简称 **/
    private String sname;
    /** 区域等级 **/
    private int level;
    /** 区域代码 **/
    private String cityCode;
    /** 邮政编码 **/
    private String postCode;
    /** 组合名称 **/
    private String merName;
    /** 经度 **/
    private String lng;
    /** 纬度 **/
    private String lat;
    /** 创建时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /** 状态 **/
    private String status;
    /** 子区域 **/
    private List<SysRegion> children;



}
