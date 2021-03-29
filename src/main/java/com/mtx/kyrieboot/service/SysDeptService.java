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

    IPage<SysDept> getAll(Page page);

    int addDept(SysDept sysDept);





}
