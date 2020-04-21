package com.mtx.kyrieboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysLogService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:12
 **/
public interface SysLogService {

    /**
     * 保存日志
     * @param request
     * @param message
     * @param name
     * @return
     */
    int saveLoginLog(HttpServletRequest request, String message, String name);


    IPage<SysLog> findSysLogPage(Page page);
}
