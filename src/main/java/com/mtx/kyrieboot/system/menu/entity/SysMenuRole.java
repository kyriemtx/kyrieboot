package com.mtx.kyrieboot.system.menu.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName SysMenuRole
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:07
 **/
@Data
@Builder
public class SysMenuRole {

    private String menuId;

    private String roleId;

    public SysMenuRole(String menuId, String roleId){
        this.menuId = menuId;
        this.roleId = roleId;
    }

    public SysMenuRole(String menuId){
        this.menuId = menuId;
    }
}
