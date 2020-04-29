package com.mtx.kyrieboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysStreet;
import com.mtx.kyrieboot.mapper.SysStreetMapper;
import com.mtx.kyrieboot.service.SysStreetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysStreetServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:36
 **/
@Service
@Slf4j
public class SysStreetServiceImpl implements SysStreetService {

    @Autowired
    private SysStreetMapper sysStreetMapper;

    @Override
    public SysStreet selectByStreetCode(String streetCode) {
        log.info("[根据街道代码查询接口],请求参数：{}",streetCode);
        SysStreet sysStreet = sysStreetMapper.selectByStreetCode(streetCode);
        log.info("[根据街道代码查询接口],返回参数：{}", JSON.toJSONString(sysStreet));
        return sysStreet;
    }

    @Override
    public SysStreet selectByStreetName(String streetName) {
        log.info("[根据街道名称查询接口],请求参数：{}",streetName);
        SysStreet sysStreet = sysStreetMapper.selectByStreetName(streetName);
        log.info("[根据街道名称查询接口],返回参数：{}", JSON.toJSONString(sysStreet));
        return sysStreet;
    }

    @Override
    public IPage<SysStreet> getAll(Page page) {
        return sysStreetMapper.getAll(page);
    }

    @Override
    public List<SysStreet> selectByAreaCode(String areaCode) {
        log.info("[根据区县代码查询接口],请求参数：{}",areaCode);
        List<SysStreet> sysStreets = sysStreetMapper.selectByAreaCode(areaCode);
        log.info("[根据区县代码查询接口],返回参数：{}", JSON.toJSONString(sysStreets));
        return sysStreets;
    }

    @Override
    public int deleteByStreetCode(String streetCode) {
        log.info("[根据街道代码删除接口],请求参数：{}",streetCode);
        int res = sysStreetMapper.deleteByStreetCode(streetCode);
        log.info("[根据街道代码删除接口],返回参数：{}",res);
        return res;
    }

    @Override
    public int insertSysStreet(SysStreet sysStreet) {
        return sysStreetMapper.insert(sysStreet);
    }

    @Override
    public int updateSysStreet(SysStreet sysStreet) {
        return sysStreetMapper.updateById(sysStreet);
    }
}
