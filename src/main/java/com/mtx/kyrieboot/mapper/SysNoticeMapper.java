package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysNotice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysNoticeMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 10:01
 **/
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {


    @Select("select * from sys_notice where id =#{id}")
    SysNotice selectByKeyId(@Param("id") int id);

    @Select("select * from sys_notice")
    IPage<SysNotice> getAll(Page page);


    @Select("select  * from sys_notice where create_by =#{createBy} and notice_title =#{noticeNTitle} and notice_type =#{noticeType}")
    List<SysNotice> selectByNotice(SysNotice sysNotice);


    @Delete("delete from sys_notice where id=#{id}")
    int deleteByKeyId(@Param("id")int id);


    @Select("select * from sys_notice")
    List<SysNotice> getAll();

    @Select("select * from sys_notice where status =#{status}")
    List<SysNotice> selectByStatus(@Param("status")String status);

    @Select("select * from sys_notice where notice_type =#{noticeType}")
    List<SysNotice> selectByType(@Param("noticeType")String noticeType);


    @Select("select * from sys_notice where notice_type =#{noticeType} and status =#{status}")
    List<SysNotice> selectForm(SysNotice sysNotice);

}
