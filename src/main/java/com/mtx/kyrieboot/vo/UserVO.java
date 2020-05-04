package com.mtx.kyrieboot.vo;

import com.mtx.kyrieboot.system.user.entity.SysUser;
import lombok.Data;

/**
 * @ClassName UserVO
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:58
 **/
@Data
public class UserVO  extends SysUser {

    private String userRole;

    public UserVO() {

    }

    public UserVO(String userRole){
        this.userRole = userRole;
    }
}
