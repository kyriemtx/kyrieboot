package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysDept;
import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 14:24
 * @Description:
 */
public interface SysDeptService {


    /**
     * 查询全部
     * @param sysDept
     * @return
     */
    List<SysDept> queryAll(SysDept sysDept);

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysDept> getAll(Page page);

    /**
     * 新增部门信息
     * @param sysDept
     * @return
     */
    int addDept(SysDept sysDept);


    /**
     * 根据主键查询
     * @param deptId
     * @return
     */
    SysDept findByDeptId(String deptId);


    /**
     * 编辑部门信息
     * @param sysDept
     * @return
     */
    int updateDeptById(SysDept sysDept);




}
