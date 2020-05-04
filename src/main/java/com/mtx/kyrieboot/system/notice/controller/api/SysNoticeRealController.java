package com.mtx.kyrieboot.system.notice.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.system.notice.entity.SysNotice;
import com.mtx.kyrieboot.system.notice.service.SysNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysNoticeRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 10:27
 **/
@RestController
@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysNoticeRealController {

    @Autowired
    private SysNoticeService sysNoticeService;


    @GetMapping("/getNoticeInfo")
    public AjaxResult getNoticeInfo(@RequestParam("page") int page,
                                    @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();
        IPage<SysNotice> sysNoticeIPage = sysNoticeService.getAll(new Page(page,pageSize));
        jsonObject.put("total",sysNoticeIPage.getTotal());
        jsonObject.put("page",sysNoticeIPage.getCurrent());
        jsonObject.put("page_size",sysNoticeIPage.getSize());
        List<SysNotice> sysNotices = sysNoticeIPage.getRecords();
        if(sysNotices.size()>0){
            for(SysNotice sysNotice:sysNotices){
                if( sysNotice.getStatus().equals("0")){
                    if(sysNotice.getNoticeType().equals("1")){
                        sysNotice.setNoticeType("通知");
                    }else {
                        sysNotice.setNoticeType("公告");
                    }
                    sysNotice.setStatus("正常");
                }else {
                    sysNotice.setStatus("关闭");
                    if(sysNotice.getNoticeType().equals("1")){
                        sysNotice.setNoticeType("通知");
                    }else {
                        sysNotice.setNoticeType("公告");
                    }
                }
            }
        }
        jsonObject.put("sysNoticeList",sysNotices);
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/selectForm")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public AjaxResult selectForm(SysNotice sysNotice){
        JSONObject jsonObject = new JSONObject();
        List<SysNotice> sysNotices = new ArrayList<>();
        if(sysNotice == null){
            sysNotices = sysNoticeService.getAll();
        }else {
            sysNotices = sysNoticeService.selectForm(sysNotice);
        }

        if(sysNotices.size()>0){
            for(SysNotice sys : sysNotices){
                if(sys.getNoticeType().equals("1")){
                    sys.setNoticeType("通知");
                }else {
                    sys.setNoticeType("公告");
                }
            }
            for(SysNotice sys : sysNotices){
                if(sys.getStatus().equals("0")){
                    sys.setStatus("正常");
                }else {
                    sys.setStatus("关闭");
                }
            }
        }
        jsonObject.put("sysNoticeList",sysNotices);
        return AjaxResult.success(jsonObject);
    }


}
