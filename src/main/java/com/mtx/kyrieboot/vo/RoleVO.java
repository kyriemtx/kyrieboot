package com.mtx.kyrieboot.vo;

import com.mtx.kyrieboot.system.role.entity.SysRole;
import lombok.Data;

/**
 * @ClassName RoleVO
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:56
 **/
@Data
public class RoleVO extends SysRole {

    private String[] ids;
}
