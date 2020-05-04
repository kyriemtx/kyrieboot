package com.mtx.kyrieboot.system.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.notice.entity.SysNotice;

import java.util.List;

/**
 * @ClassName SysNoticeService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 10:08
 **/
public interface SysNoticeService {

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SysNotice selectByPrimaryKey(int id);


    /**
     * 查询表单查询
     * @param sysNotice
     * @return
     */
    List<SysNotice> selectByNotice(SysNotice sysNotice);


    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysNotice> getAll(Page page);

    /**
     * 新增公告信息
     * @param sysNotice
     * @return
     */
    int insertSysNotice(SysNotice sysNotice);

    /**
     * 修改公告信息
     * @param sysNotice
     * @return
     */
    int updateSysNotice(SysNotice sysNotice);


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);



    List<SysNotice> getAll();


    List<SysNotice> selectForm(SysNotice sysNotice);

}
