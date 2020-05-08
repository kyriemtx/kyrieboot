package com.mtx.kyrieboot.kyriegen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.kyriegen.entity.ColumnEntity;
import com.mtx.kyrieboot.kyriegen.entity.ReferencedTable;
import com.mtx.kyrieboot.kyriegen.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName KyrieGenMapper
 * @Description
 * @Author tengxio.ma
 * @Date 2020/5/8 11:15
 * @Version 1.0
 **/
@Mapper
public interface KyrieGenMapper {

    IPage<TableEntity> kyrieQueryTableList(Page page);

    TableEntity selectByTableName(String tableName);

    String queryDatabaseName();

    List<Map<String, String>> queryColumns(String tableName);

    List<ColumnEntity>  selectAllColumns(String tableName);

    List<ReferencedTable> queryReferenced(String tableName);


}
