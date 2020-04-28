package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysProvince;

/**
 * @ClassName SysProvinceService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 11:50
 **/
public interface SysProvinceService {

    /**
     * 根据省份名称查询
     * @param provinceName
     * @return
     */
    SysProvince findByName(String provinceName);

    /**
     * 根据省份代码查询
     * @param provinceCode
     * @return
     */
    SysProvince findByCode(String provinceCode);


    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysProvince> getAll(Page page);

    /**
     * 根据ID查询
     * @param Id
     * @return
     */
    SysProvince selectById(int Id);


    /**
     * 新增省份信息
     * @param sysProvince
     * @return
     */
    int insertSysProvince(SysProvince sysProvince);


    /**
     * 修改省份信息
     * @param sysProvince
     * @return
     */
    int updateSysProvince(SysProvince sysProvince);

    /**
     * 删除岗位信息
     * @param Id
     * @return
     */
    int deleteSysProvince(int Id);


}
