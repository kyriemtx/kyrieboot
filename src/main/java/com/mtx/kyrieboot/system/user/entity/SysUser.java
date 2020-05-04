package com.mtx.kyrieboot.system.user.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

/**
 * @ClassName SysUser
 * @Description 用户实体类
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:00
 **/
@Data
public class SysUser  extends BaseRowModel {

    private static final long serialVersionUID = -3747386723206797631L;

    private String id;

    @ExcelProperty(value = "账号",index = 0)
    private String name;

    private String password;

    @ExcelProperty(value = "用户昵称",index = 1)
    private String nickName;

    @ExcelProperty(value = "性别",index = 2)
    private String sex;

    @ExcelProperty(value = "手机号",index = 3)
    private String mobile;

    @ExcelProperty(value = "邮箱",index = 4)
    private String email;

    @ExcelProperty(value = "生日",index = 5)
    private String birthday;

    @ExcelProperty(value = "爱好",index = 6)
    private String hobby;

    @ExcelProperty(value = "通信地址",index = 7)
    private String liveAddress;

    @ExcelProperty(value = "创建时间",index = 8)
    private Date createTime;

    private Date updateTime;

    public SysUser(){

    }

    public SysUser(String id, String name, String password, String nickName, String sex, String mobile,
                   String email, String birthday, String hobby, String liveAddress){
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
        this.liveAddress = liveAddress;
    }
}
