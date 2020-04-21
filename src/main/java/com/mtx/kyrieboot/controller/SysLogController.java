package com.mtx.kyrieboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysLogController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:04
 **/
@Controller
@RequestMapping("/sys_log")
public class SysLogController {

    @GetMapping("/list")
    public String index(){
        return "module/syslog/syslog";
    }
}
