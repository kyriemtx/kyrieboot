package com.mtx.kyrieboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysDept;
import com.mtx.kyrieboot.mapper.SysDeptMapper;
import com.mtx.kyrieboot.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 14:24
 * @Description:
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {


    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Override
    public List<SysDept> queryAll(SysDept sysDept) {
        return sysDeptMapper.queryAll(sysDept);
    }

    @Override
    public IPage<SysDept> getAll(Page page) {
        return sysDeptMapper.findByPage(page);
    }

    @Override
    public int addDept(SysDept sysDept) {
        return 0;
    }


}
