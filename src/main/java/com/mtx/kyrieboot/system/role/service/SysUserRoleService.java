package com.mtx.kyrieboot.system.role.service;

import com.mtx.kyrieboot.system.role.entity.SysUserRole;

/**
 * @ClassName SysUserRoleService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:14
 **/
public interface SysUserRoleService {

    /**
     * 添加用户和角色的联系
     * @param sysUserRole 用户角色
     * @return 返回值
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 根据用户id删除用户和角色的联系
     * @param userId 用户id
     * @return 返回值
     */
    int deleteByUserId(String userId);
}
