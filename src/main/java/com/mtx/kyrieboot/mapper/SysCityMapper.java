package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysCity;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysCityMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 12:53
 **/

@Mapper
public interface SysCityMapper extends BaseMapper<SysCity> {

    /**
     * 根据城市代码查询
     * @param cityCode
     * @return
     */
    @Select("select * from sys_city where city_code =#{cityCode}")
    SysCity selectByCode(@Param("cityCode") String cityCode);

    /**
     * 根据城市名称查询
     * @param cityName
     * @return
     */
    @Select("select * from sys_city where city_name =#{cityName}")
    SysCity selectByName(@Param("cityName") String cityName);


    @Select("select * from sys_city where id=#{id}")
    SysCity selectByKeyId(@Param("id") int id);


    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from sys_city")
    IPage<SysCity> getAll(Page page);


    @Select("select * from sys_city where id=#{id}")
    SysCity selectById(@Param("id")int id);


    @Delete("delete from sys_city where city_code =#{cityCode}")
    int deleteByCode(@Param("cityCode")String cityCode);

    @Select("select city_name from sys_city where city_code =#{cityCode}")
    String selectNameByCode(@Param("cityCode")String cityCode);


    @Select("select * from sys_city where province_code = #{provinceCode}")
    List<SysCity> selectCitesByProvinceCode(@Param("provinceCode")String provinceCode);


    @Update("update sys_city set province_code = #{provinceCode},city_code = #{cityCode},city_name = #{cityName}," +
            "short_name =#{shortName}, lng =#{lng},lat =#{lat},sort =#{sort}, memo =#{memo},data_state =#{dataState} where id =#{id} ")
    int updateCityByCity(SysCity sysCity);

    @Select("select * from sys_city")
    List<SysCity> areaSelect();

}
