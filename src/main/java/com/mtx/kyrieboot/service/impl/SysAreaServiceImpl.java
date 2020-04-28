package com.mtx.kyrieboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysArea;
import com.mtx.kyrieboot.mapper.SysAreaMapper;
import com.mtx.kyrieboot.service.SysAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysAreaServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:45
 **/
@Service
@Slf4j
public class SysAreaServiceImpl implements SysAreaService {

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Override
    public SysArea selectByCode(String areaCode) {
        log.info("[根据县区代码查询接口]，请求参数： areaCode :{}",areaCode);
        SysArea sysArea = sysAreaMapper.selectByCode(areaCode);
        log.info("[根据县区代码查询接口]，返回参数：{}", JSON.toJSONString(sysArea));
        return sysArea;
    }

    @Override
    public SysArea selectByName(String areaName) {
        log.info("[根据县区名称查询接口]，请求参数： areaName :{}",areaName);
        SysArea sysArea = sysAreaMapper.selectByName(areaName);
        log.info("[根据县区名称查询接口]，返回参数：{}", JSON.toJSONString(sysArea));
        return sysArea;
    }

    @Override
    public IPage<SysArea> getAll(Page page) {
        return sysAreaMapper.getAll(page);
    }

    @Override
    public List<SysArea> selectAreasByCityCode(String cityCode) {
        log.info("[根据上级代码查询下属区县列表接口]，请求参数： cityCode :{}",cityCode);
        List<SysArea> sysAreas = sysAreaMapper.selectAreasByCityCode(cityCode);
        log.info("[根据上级代码查询下属区县列表接口]，返回参数：{}", JSON.toJSONString(sysAreas));
        return sysAreas;
    }

    @Override
    public int deleteByAreaCode(String areaCode) {
        log.info("[根据区县代码删除区县信息接口]，请求参数：areaCode {}",areaCode);
        int res = sysAreaMapper.deleteByAreaCode(areaCode);
        log.info("[根据区县代码删除区县信息接口]，返回参数：res {}",res);
        return res;
    }


    @Override
    public int insertSysArea(SysArea sysArea) {
        return sysAreaMapper.insert(sysArea);
    }

    @Override
    public int updateSysArea(SysArea sysArea) {
        return sysAreaMapper.updateById(sysArea);
    }
}
