package com.mtx.kyrieboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtx.kyrieboot.entity.SysMenuRole;
import com.mtx.kyrieboot.mapper.SysMenuRoleMapper;
import com.mtx.kyrieboot.service.SysMenuRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysMenuRoleServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:50
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuRoleServiceImpl implements SysMenuRoleService {

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    public int addMenuRole(SysMenuRole sysMenuRole) {
        return sysMenuRoleMapper.addMenuRole(sysMenuRole);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return sysMenuRoleMapper.deleteById(roleId);
    }

    @Override
    public List<String> getAllMenuId(String roleId, List<String> parentIds) {
        QueryWrapper<SysMenuRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("menu_id").eq("role_id",roleId).notIn("menu_id",parentIds);
        List<SysMenuRole> sysMenuRoles = sysMenuRoleMapper.selectList(queryWrapper);
        return sysMenuRoles.stream().map(o -> o.getMenuId()).collect(Collectors.toList());
    }
}
