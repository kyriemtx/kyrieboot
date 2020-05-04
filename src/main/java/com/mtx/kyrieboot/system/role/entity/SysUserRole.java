package com.mtx.kyrieboot.system.role.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName SysUserRole
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:03
 **/
@Data
@Builder
public class SysUserRole {

    private String userId;

    private String roleId;

    public SysUserRole(){

    }

    public SysUserRole(String userId, String roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}
