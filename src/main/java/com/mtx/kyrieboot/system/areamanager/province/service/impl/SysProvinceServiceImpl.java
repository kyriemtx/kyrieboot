package com.mtx.kyrieboot.system.areamanager.province.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.areamanager.province.entity.SysProvince;
import com.mtx.kyrieboot.system.areamanager.province.mapper.SysProvinceMapper;
import com.mtx.kyrieboot.system.areamanager.province.service.SysProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysProvinceServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 11:53
 **/
@Slf4j
@Service
public class SysProvinceServiceImpl implements SysProvinceService {

    @Autowired
    private SysProvinceMapper sysProvinceMapper;

    @Override
    public SysProvince findByName(String provinceName) {
        log.info("[根据省份名称查询省份接口]，请求参数：provinceName {}",provinceName);
        SysProvince sysProvince = sysProvinceMapper.selectByName(provinceName);
        log.info("[根据省份名称查询省份接口]，返回参数：{}", JSON.toJSONString(sysProvince));
        return sysProvince;
    }

    @Override
    public SysProvince findByCode(String provinceCode) {
        log.info("[根据省份代码查询省份接口]，请求参数：provinceCode {}",provinceCode);
        SysProvince sysProvince = sysProvinceMapper.selectByCode(provinceCode);
        log.info("[根据省份代码查询省份接口]，返回参数：{}", JSON.toJSONString(sysProvince));
        return sysProvince;
    }

    @Override
    public IPage<SysProvince> getAll(Page page) {
        return sysProvinceMapper.getAll(page);
    }

    @Override
    public SysProvince selectById(int id) {
        log.info("[根据ID查询省份接口]，请求参数：id {}",id);
        SysProvince sysProvince = sysProvinceMapper.selectById(id);
        log.info("[根据ID查询省份接口]，返回参数：{}", JSON.toJSONString(sysProvince));
        return sysProvince;
    }

    @Override
    public int insertSysProvince(SysProvince sysProvince) {
        log.info("[新增省份信息接口]，请求参数： {}",JSON.toJSONString(sysProvince));
        int res = sysProvinceMapper.insert(sysProvince);
        log.info("新增省份信息接口]，返回参数：{}", res);
        return res;
    }

    @Override
    public int updateSysProvince(SysProvince sysProvince) {
        log.info("[修改省份信息接口]，请求参数： {}",JSON.toJSONString(sysProvince));
        int res = sysProvinceMapper.updateSysProvince(sysProvince);
        log.info("[修改省份信息接口]，返回参数：{}", res);
        return res;
    }

    @Override
    public int deleteSysProvince(int Id) {
        log.info("[删除省份信息接口]，请求参数： {}",Id);
        int res = sysProvinceMapper.deleteById(Id);
        log.info("[删除省份信息接口]，返回参数：{}", res);
        return res;
    }

    @Override
    public List<SysProvince> citySelect() {
        return sysProvinceMapper.citySelect();
    }

    @Override
    public String selectProvinceNameByCode(String provinceCode) {
        return sysProvinceMapper.selectProvinceNameByCode(provinceCode);
    }

    @Override
    public int deleteByProvinceCode(String provinceCode) {
        return sysProvinceMapper.deleteByProvinceCode(provinceCode);
    }

    @Override
    public List<SysProvince> selectForm(SysProvince sysProvince) {
        List<SysProvince> sysProvinces = sysProvinceMapper.queryAll(sysProvince);
        return sysProvinces;
    }

}
