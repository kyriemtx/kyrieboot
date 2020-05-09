package com.mtx.kyrieboot.devtool.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SwaggerController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/9 11:25
 * @Version 1.0
 **/
@Controller
@RequestMapping("/swagger")
public class SwaggerController {

    @GetMapping("/index")
    public String index(){
        return "module/devutils/swagger";
    }

}
