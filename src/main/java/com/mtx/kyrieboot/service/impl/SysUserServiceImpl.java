package com.mtx.kyrieboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysUser;
import com.mtx.kyrieboot.mapper.SysUserMapper;
import com.mtx.kyrieboot.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 17:39
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private  SysUserMapper sysUserMapper;

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public IPage<SysUser> getAll(Page page) {
        return sysUserMapper.getAll(page);
    }

    @Override
    public SysUser getById(String id) {
        return sysUserMapper.getById(id);
    }

    @Override
    public int deleteById(String id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    public int updateById(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updatePasswordById(String password, String id) {
       return sysUserMapper.updatePasswordById(password,id);
    }

    @Override
    public List<SysUser> selectForm(SysUser sysUser) {
        if(sysUser.getNickName() == null || sysUser.getNickName().equals("")){
            if(sysUser.getSex() == null || sysUser.getSex().equals("")){
                return sysUserMapper.selectAll();
            }else {
                return sysUserMapper.findBySex(sysUser.getSex());
            }
        }else if(sysUser.getSex() == null || sysUser.getSex().equals("")){
            if(sysUser.getNickName() == null || sysUser.getNickName().equals("")){
                return sysUserMapper.selectAll();
            }else {
                return sysUserMapper.findByNickName(sysUser.getNickName());
            }
        }else {
            return sysUserMapper.selectAll();
        }
    }
}
