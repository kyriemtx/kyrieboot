package com.mtx.kyrieboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysCity;
import com.mtx.kyrieboot.mapper.SysCityMapper;
import com.mtx.kyrieboot.service.SysCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysCityServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 13:00
 **/
@Service
@Slf4j
public class SysCityServiceImpl implements SysCityService {

    @Autowired
    private SysCityMapper sysCityMapper;

    @Override
    public SysCity selectByCode(String cityCode) {
        log.info("[根据城市代码查询城市信息接口]，请求参数 cityCode：{}",cityCode);
        SysCity sysCity = sysCityMapper.selectByCode(cityCode);
        log.info("[根据城市代码查询城市信息接口]，返回参数：{}", JSON.toJSONString(sysCity));
        return sysCity;
    }

    @Override
    public SysCity selectByName(String cityName) {
        log.info("[根据城市名称查询城市信息接口]，请求参数 cityCode：{}",cityName);
        SysCity sysCity = sysCityMapper.selectByName(cityName);
        log.info("[根据城市名称查询城市信息接口]，返回参数：{}", JSON.toJSONString(sysCity));
        return sysCity;
    }

    @Override
    public SysCity selectByKeyId(int id) {
        return sysCityMapper.selectByKeyId(id);
    }

    @Override
    public IPage<SysCity> getAll(Page page) {

       return sysCityMapper.getAll(page);
    }

    @Override
    public SysCity selectById(int id) {
        return  sysCityMapper.selectById(id);
    }

    @Override
    public int inserSysCity(SysCity sysCity) {
        return sysCityMapper.insert(sysCity);
    }

    @Override
    public int updataSysCity(SysCity sysCity) {
        return sysCityMapper.updateCityByCity(sysCity);
    }

    @Override
    public int deleteByCode(String cityCode) {
        return sysCityMapper.deleteByCode(cityCode);
    }

    @Override
    public String selectNameByCode(String cityCode) {
        return sysCityMapper.selectNameByCode(cityCode);
    }


    @Override
    public List<SysCity> selectCitesByProvinceCode(String provinceCode) {
        log.info("[根据省份代码查询下属城市接口],请求参数：provinceCode :{}",provinceCode);
        List<SysCity> sysCities = sysCityMapper.selectCitesByProvinceCode(provinceCode);
        log.info("[根据省份代码查询下属城市接口],返回参数 :{}",JSON.toJSONString(sysCities));
        return sysCities;
    }

    @Override
    public List<SysCity> areaSelect() {
        return sysCityMapper.areaSelect();
    }
}
