package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysArea;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysAreaMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:37
 **/
@Mapper
public interface SysAreaMapper extends BaseMapper<SysArea> {

    /**
     * 根据区域代码查询
     * @param areaCode
     * @return
     */
    @Select("select * from sys_area where area_code =#{areaCode}")
    SysArea selectByCode(@Param("areaCode") String areaCode);


    /**
     * 根据区域名称查询
     * @param areaName
     * @return
     */
    @Select("select * from sys_area where area_name =#{areaName}")
    SysArea selectByName(@Param("areaName") String areaName);


    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from sys_area")
    IPage<SysArea> getAll(Page page);


    /**
     * 根据城市代码查询下属的县区信息
     * @param cityCode
     * @return
     */
    @Select("select * from sys_area where city_code = #{cityCode}")
    List<SysArea> selectAreasByCityCode(@Param("cityCode") String cityCode);


    @Delete("delete from sys_area where area_code =#{areaCode}")
    int deleteByAreaCode(@Param("areaCode") String areaCode);

}
