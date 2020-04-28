package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysCity;

import java.util.List;

/**
 * @ClassName SysCityService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 12:57
 **/
public interface SysCityService {


    /**
     * 根据城市代码查询
     * @param cityCode
     * @return
     */
    SysCity selectByCode(String cityCode);

    /**
     * 根据城市名查询
     * @param cityName
     * @return
     */
    SysCity selectByName(String cityName);

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysCity> getAll(Page page);


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    SysCity selectById(int id);


    /**
     * 新增城市
     * @param sysCity
     * @return
     */
    int inserSysCity(SysCity sysCity);


    /**
     * 更新城市信息
     * @param sysCity
     * @return
     */
    int updataSysCity(SysCity sysCity);


    /**
     * 根据cityCode删除
     * @param cityCode
     * @return
     */
    int deleteByCode(String cityCode);


    /**
     * 根据城市代码查询城市名(供区县管理页面展区县所属的上级城市)
     * @param cityCode
     * @return
     */
    String selectNameByCode(String cityCode);

    /**
     * 根据省份代码查询下属城市
     * @param provinceCode
     * @return
     */
    List<SysCity> selectCitesByProvinceCode(String provinceCode);

}
