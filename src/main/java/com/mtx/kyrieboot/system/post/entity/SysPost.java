package com.mtx.kyrieboot.system.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysPost
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 11:31
 **/
@Data
@Builder
public class SysPost implements Serializable {


    private static final long serialVersionUID = 5205554920163069741L;


    private String postId;
    private String postCode;
    private String postName;
    private int postSort;
    private String postStatus;
    private String creatBy;
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String remark;


    public SysPost(){

    }

    public SysPost(String postId, String postCode, String postName, int postSort, String postStatus, String creatBy,
                   String updateBy, Date createTime, Date updateTime, String remark) {
        this.postId = postId;
        this.postCode = postCode;
        this.postName = postName;
        this.postSort = postSort;
        this.postStatus = postStatus;
        this.creatBy = creatBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }
}
