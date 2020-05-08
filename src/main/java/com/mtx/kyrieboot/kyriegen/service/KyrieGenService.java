package com.mtx.kyrieboot.kyriegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.kyriegen.entity.ColumnEntity;
import com.mtx.kyrieboot.kyriegen.entity.ReferencedTable;
import com.mtx.kyrieboot.kyriegen.entity.TableEntity;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName KyrieGenService
 * @Description
 * @Author tengxio.ma
 * @Date 2020/5/8 11:29
 * @Version 1.0
 **/
public interface KyrieGenService {

    /**
     * 查询表列表
     * @return
     */
    IPage<TableEntity> kyrieQueryTableList(Page page);

    /**
     * 根据表名查询
     * @param tableName
     * @return
     */
    TableEntity selectByTableName(String tableName);

    /**
     * 获取列
     * @param tableName
     * @return
     */
    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 获取列
     * @param tableName
     * @return
     */
    List<ColumnEntity>  selectAllColumns(String tableName);

    /**
     * 获取关联表
     * @param tableName
     * @return
     */
    List<ReferencedTable> queryReferenced(String tableName);


}
