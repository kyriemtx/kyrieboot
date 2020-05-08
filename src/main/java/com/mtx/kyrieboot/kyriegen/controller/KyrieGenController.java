package com.mtx.kyrieboot.kyriegen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName KyrieGenController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:46
 * @Version 1.0
 **/
@Controller
@RequestMapping("/gen")
public class KyrieGenController {

    @GetMapping("/list")
    public String index(){
        return "module/kyriegen/generator";
    }

}
