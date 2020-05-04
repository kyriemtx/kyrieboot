package com.mtx.kyrieboot.devtool.build;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BuildController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/27 10:55
 **/

@Controller
@RequestMapping("/build")
public class BuildController {

    @GetMapping("/build")
    public String build(){
        return "module/build/build";
    }
}
