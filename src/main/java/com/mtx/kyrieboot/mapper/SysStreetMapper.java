package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysStreet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysStreetMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:24
 **/
@Mapper
public interface SysStreetMapper extends BaseMapper<SysStreet> {

    /**
     * 根据街道编码查询
     * @param streetCode
     * @return
     */
    @Select("select * from sys_street where street_code =#{streetCode}")
    SysStreet selectByStreetCode(@Param("streetCode") String streetCode);


    /**
     * 根据街道名称查询
     * @param streetName
     * @return
     */
    @Select("select * from sys_street where street_name =#{streetName}")
    SysStreet selectByStreetName(@Param("streetName") String streetName);


    @Select("select * from sys_street")
    IPage<SysStreet> getAll(Page page);

    /**
     * 根据区县代码查下属街道集合
     * @param areaCode
     * @return
     */
    @Select("select * from sys_street where area_code =#{areaCode}")
    List<SysStreet> selectByAreaCode(@Param("areaCode") String areaCode);

    /**
     * 根据街道编码删除信
     * @param streetCode
     * @return
     */
    @Delete("delete from sys_street where area_code =#{streetCode}")
    int deleteByStreetCode(@Param("streetCode") String streetCode);


    List<SysStreet> queryAll(SysStreet sysStreet);

}
