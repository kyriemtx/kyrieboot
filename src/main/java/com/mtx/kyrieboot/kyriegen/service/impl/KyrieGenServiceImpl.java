package com.mtx.kyrieboot.kyriegen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.kyriegen.entity.ColumnEntity;
import com.mtx.kyrieboot.kyriegen.entity.ReferencedTable;
import com.mtx.kyrieboot.kyriegen.entity.TableEntity;
import com.mtx.kyrieboot.kyriegen.mapper.KyrieGenMapper;
import com.mtx.kyrieboot.kyriegen.service.KyrieGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName KyrieGenServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:34
 * @Version 1.0
 **/
@Service
@Slf4j
public class KyrieGenServiceImpl implements KyrieGenService {

    @Autowired
    private KyrieGenMapper kyrieGenMapper;

    @Override
    public IPage<TableEntity> kyrieQueryTableList(Page page) {
        log.info("[获取所有数据表列表]");
        return kyrieGenMapper.kyrieQueryTableList(page);
    }

    @Override
    public TableEntity selectByTableName(String tableName) {
        log.info("[根据表名查询数据表]，请求参数：{}",tableName);
        return kyrieGenMapper.selectByTableName(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        log.info("[根据表名查询列信息]，请求参数：{}",tableName);
       return kyrieGenMapper.queryColumns(tableName);
    }

    @Override
    public List<ColumnEntity> selectAllColumns(String tableName) {
        log.info("[查询列信息]，请求参数：{}",tableName);
        return kyrieGenMapper.selectAllColumns(tableName);
    }

    @Override
    public List<ReferencedTable> queryReferenced(String tableName) {
        log.info("[查询相关表]，请求参数：{}",tableName);
       return kyrieGenMapper.queryReferenced(tableName);
    }
}
