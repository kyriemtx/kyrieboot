package com.mtx.kyrieboot.system.areamanager.area.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.areamanager.area.entity.SysArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SysAreaService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:44
 **/
public interface SysAreaService {


    /**
     * 根据区域代码查询
     * @param areaCode
     * @return
     */
    SysArea selectByCode(@Param("areaCode") String areaCode);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SysArea selectByKeyId(int id);


    /**
     * 根据区域名称查询
     * @param areaName
     * @return
     */
    SysArea selectByName(@Param("areaName") String areaName);


    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysArea> getAll(Page page);


    /**
     * 根据城市代码查询下属的县区信息
     * @param cityCode
     * @return
     */
    List<SysArea> selectAreasByCityCode(@Param("cityCode") String cityCode);


    /**
     * 根据区域代码删除
     * @param areaCode
     * @return
     */
    int deleteByAreaCode(@Param("areaCode") String areaCode);


    /**
     * 新增
     * @param sysArea
     * @return
     */
    int insertSysArea(SysArea sysArea);

    /**
     * 修改
     * @param sysArea
     * @return
     */
    int updateSysArea(SysArea sysArea);

    /**
     * 查询区县列表  供街道管理模块下拉框选择
     * @return
     */
    List<SysArea> streetSelect();


    List<SysArea> selectForm(SysArea sysArea);

}
