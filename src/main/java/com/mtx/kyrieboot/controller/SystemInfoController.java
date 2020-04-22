package com.mtx.kyrieboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SystemInfoController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:05
 **/
@Controller
@RequestMapping("/system")
public class SystemInfoController {

    @GetMapping("/serverInfo")
    public String serverInfo(){
        return "module/system/server";
    }

}
