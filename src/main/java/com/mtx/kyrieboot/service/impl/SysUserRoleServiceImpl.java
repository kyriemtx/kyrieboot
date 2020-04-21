package com.mtx.kyrieboot.service.impl;

import com.mtx.kyrieboot.entity.SysUserRole;
import com.mtx.kyrieboot.mapper.SysUserRoleMapper;
import com.mtx.kyrieboot.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserRoleServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 17:02
 **/

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserRoleServiceImpl implements SysUserRoleService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int insert(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public int deleteByUserId(String userId) {
        return sysUserRoleMapper.deleteByUserId(userId);
    }
}
