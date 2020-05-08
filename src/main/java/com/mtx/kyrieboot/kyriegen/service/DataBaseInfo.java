package com.mtx.kyrieboot.kyriegen.service;

import com.mtx.kyrieboot.kyriegen.entity.TableEntity;

import java.util.List;

/**
 * @ClassName DataBaseInfo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:32
 * @Version 1.0
 **/
public abstract  class DataBaseInfo {

    /****
     * 获取数据库名称
     *
     * @return
     */
    public abstract String queryDatabaseName();


    /***
     * 获取表的信息
     *
     * @return
     */
    public  abstract List<TableEntity> getTableList();

}
