package com.mtx.kyrieboot.system.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.post.entity.SysPost;

import java.util.List;

/**
 * @ClassName SysPostService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 12:32
 **/
public interface SysPostService {

    /**
     * 根据名称查询
     * @param postName
     * @return
     */
    SysPost findByName(String postName);

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysPost> getAll(Page page);

    List<SysPost> getAll();

    /**
     * 根据ID查询
     * @param postId
     * @return
     */
    SysPost selectById(String postId);


    /**
     * 新增岗位信息
     * @param sysPost
     * @return
     */
    int insertSysPost(SysPost sysPost);


    /**
     * 修改岗位信息
     * @param sysPost
     * @return
     */
    int updateSysPost(SysPost sysPost);

    /**
     * 删除岗位信息
     * @param postId
     * @return
     */
    int deleteSysPost(String postId);


    /**
     * 查询表单
     * @param sysPost
     * @return
     */
    List<SysPost> selectForm(SysPost sysPost);

}
