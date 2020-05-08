package com.mtx.kyrieboot.kyriegen.entity;

import lombok.Data;

/**
 * @ClassName ReferencedTable
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:07
 * @Version 1.0
 **/
@Data
public class ReferencedTable {

    private String tableName;
    private String columnName;
    private String referencedTableName;
    private String referencedColumnName;
    private String columnNameJava;
    private String referencedTableNameJava;
    private String referencedColumnNameJava;
}
