package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysRegion;
import com.mtx.kyrieboot.vo.SysRegionListVO;

import java.util.List;

/**
 * @ClassName SysRegionService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 11:59
 **/
public interface SysRegionService {

    /**
     * 根据区域代码查询该区域及其子区域信息
     * @param id
     * @return
     */
    SysRegionListVO selectRegions(int id);


    /**
     * 查询当前区域下面的所有子区域
     * @param pid
     * @return
     */
    List<SysRegion> selectByPid(int pid);


    /**
     * 组装一级区域信息
     * @param sysRegion
     * @return
     */
    List<SysRegion> selectFirstLevel(SysRegion sysRegion);



    /**
     * 组装二级区域信息
     * @param sysRegions
     * @return
     */
    List<SysRegion> selectSecondLevel(List<SysRegion> sysRegions);

    /**
     * 组装三级区域信息
     * @param sysRegions
     * @return
     */
    List<SysRegion> selectThirdLevel(List<SysRegion> sysRegions);

    /**
     * 根据区域代码查询区域信息
     * @param id
     * @return
     */
    SysRegion selectRegionById(int id);

    /**
     * 查询顶级区域
     * @param page
     * @return
     */
    IPage<SysRegion> topLevel(Page page);

}
