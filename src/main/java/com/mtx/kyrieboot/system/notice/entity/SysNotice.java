package com.mtx.kyrieboot.system.notice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysNotice
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 9:56
 **/
@Data
public class SysNotice implements Serializable {

    private static final long serialVersionUID = -4503664079719624915L;

    private int id;

    private String noticeTitle;

    private String noticeType;

    private String noticeNContent;

    private String status;

    private String createBy;

    private String gmtCreate;

    private String updateBy;

    private String gmtModified;

    private String remark;

}
