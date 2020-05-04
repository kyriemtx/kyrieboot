package com.mtx.kyrieboot.system.notice.controller;

import com.mtx.kyrieboot.system.notice.entity.SysNotice;
import com.mtx.kyrieboot.system.notice.service.SysNoticeService;
import com.mtx.kyrieboot.system.notice.service.SysNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysNoticeController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 10:24
 **/
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysNoticeController {


    @Autowired
    private SysNoticeService sysNoticeService;


    @GetMapping("/list")
    public String index(){
        return "module/notice/notice";
    }


    @GetMapping("/update")
    public String update(int id, Model model){
        SysNotice sysNotice = sysNoticeService.selectByPrimaryKey(id);
        model.addAttribute("sysNotice",sysNotice);
        return "module/notice/update";
    }


    @GetMapping("/add")
    public String add(){
        return "module/notice/add";
    }
}
