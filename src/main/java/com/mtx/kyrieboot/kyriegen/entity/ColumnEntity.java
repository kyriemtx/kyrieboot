package com.mtx.kyrieboot.kyriegen.entity;

import lombok.Data;

/**
 * @ClassName ColumnEntity
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:06
 * @Version 1.0
 **/
@Data
public class ColumnEntity {

    //列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;

    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;

    //是否索引 是否主键
    private String columnKey;

    //是否为空
    private String isNullable;

    //默认值
    private String columnDefault;

    //数据长度
    private String dataLength;

}
