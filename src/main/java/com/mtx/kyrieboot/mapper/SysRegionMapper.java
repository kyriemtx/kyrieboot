package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysRegionMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 12:05
 **/
@Mapper
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    @Select("select * from sys_region where id =#{id}")
    SysRegion selectRegionById(@Param("id") int id);



    @Select("select * from sys_region where pid =#{pid}")
    List<SysRegion> selectRegionByPid(@Param("pid") int pid);


    @Select("SELECT * FROM sys_region WHERE level = 0")
    IPage<SysRegion> topLevel(Page page);


}
