package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName SysLogMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:06
 **/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    @Select("SELECT * FROM sys_log ORDER BY create_date desc")
    IPage<SysLog> findSysLogPage(Page page);
}
