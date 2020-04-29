package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysStreet;

import java.util.List;

/**
 * @ClassName SysStreetService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:33
 **/
public interface SysStreetService {

    /**
     * 根据街道编码查询
     * @param streetCode
     * @return
     */
    SysStreet selectByStreetCode(String streetCode);


    /**
     * 根据街道名称查询
     * @param streetName
     * @return
     */
    SysStreet selectByStreetName(String streetName);


    IPage<SysStreet> getAll(Page page);

    /**
     * 根据区县代码查下属街道集合
     * @param areaCode
     * @return
     */
    List<SysStreet> selectByAreaCode(String areaCode);

    /**
     * 根据街道编码删除信
     * @param streetCode
     * @return
     */
    int deleteByStreetCode(String streetCode);


    /**
     * 新增街道信息
     * @param sysStreet
     * @return
     */
    int insertSysStreet(SysStreet sysStreet);

    /**
     * 修改街道信息
     * @param sysStreet
     * @return
     */
    int updateSysStreet(SysStreet sysStreet);


}
