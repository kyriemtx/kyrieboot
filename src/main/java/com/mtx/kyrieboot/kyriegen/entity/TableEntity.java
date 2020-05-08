package com.mtx.kyrieboot.kyriegen.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName TableEntity
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:03
 * @Version 1.0
 **/
@Data
public class TableEntity {

    private String tableName;
    private String tableComment;
    private ColumnEntity pk;
    private List<ColumnEntity> columns;
    private String className;
    private String classname;
    private String createTime;
    private String updateTime;
    private Boolean hasBigDecimal;
    private List<ReferencedTable> listReferencedTable;
}
