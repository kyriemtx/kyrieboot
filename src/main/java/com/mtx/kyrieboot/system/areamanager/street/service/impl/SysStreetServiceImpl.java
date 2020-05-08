package com.mtx.kyrieboot.system.areamanager.street.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.areamanager.area.entity.SysArea;
import com.mtx.kyrieboot.system.areamanager.area.service.SysAreaService;
import com.mtx.kyrieboot.system.areamanager.street.entity.SysStreet;
import com.mtx.kyrieboot.system.areamanager.street.mapper.SysStreetMapper;
import com.mtx.kyrieboot.system.areamanager.street.service.SysStreetService;
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
    @Autowired
    private SysAreaService sysAreaService;

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

    @Override
    public List<SysStreet> selectForm(SysStreet sysStreet) {
       if(sysStreet != null){
           if(sysStreet.getAreaName() != null){
               SysArea sysArea = sysAreaService.selectByName(sysStreet.getAreaName());
               if(sysArea != null){
                   sysStreet.setAreaCode(sysArea.getAreaCode());
               }
           }
       }
        return sysStreetMapper.queryAll(sysStreet);
    }
}