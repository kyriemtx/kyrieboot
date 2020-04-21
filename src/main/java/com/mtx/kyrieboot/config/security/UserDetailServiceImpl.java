package com.mtx.kyrieboot.config.security;

import com.mtx.kyrieboot.entity.SysRole;
import com.mtx.kyrieboot.entity.SysUser;
import com.mtx.kyrieboot.service.SysRoleService;
import com.mtx.kyrieboot.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName UserDetailServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:25
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 查询用户
        SysUser sysUser = sysUserService.findByName(username);
        if (sysUser != null) {
            // 查询权限
            SysRole sysRole = sysRoleService.findByUserId(sysUser.getId());
            authorities.add(new SimpleGrantedAuthority(sysRole.getAuthority()));
            return new User(username,sysUser.getPassword(),authorities);
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }
}

