package com.mtx.kyrieboot.devtool.icon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName DevUtilsController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 18:52
 **/
@Controller
@RequestMapping("/devUtils")
public class DevUtilsController {

    @GetMapping("/menuIcon")
    public String meunIcon(){
        return "module/devutils/menuIcon";
    }
    @GetMapping("/vCharts")
    public String vcharts() {
        return "module/devutils/vCharts";
    }
}
