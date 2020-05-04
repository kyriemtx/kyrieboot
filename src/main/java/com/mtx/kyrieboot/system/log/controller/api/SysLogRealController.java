package com.mtx.kyrieboot.system.log.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.system.log.entity.SysLog;
import com.mtx.kyrieboot.system.log.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysLogRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:15
 **/
@RestController
@RequestMapping("/sysLog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogRealController {

    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/geSysLoglist")
    public AjaxResult getMenulist(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize){
        IPage<SysLog> sysLogPage = sysLogService.findSysLogPage(new Page(page, pageSize));
        JSONObject data = new JSONObject(16);
        data.put("total",sysLogPage.getTotal());
        data.put("sysLogList",sysLogPage.getRecords());
        data.put("page",sysLogPage.getCurrent());
        data.put("page_size",sysLogPage.getSize());
        return AjaxResult.success(data);
    }


}
