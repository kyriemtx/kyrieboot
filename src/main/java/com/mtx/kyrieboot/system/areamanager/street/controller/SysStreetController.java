package com.mtx.kyrieboot.system.areamanager.street.controller;

import com.mtx.kyrieboot.system.areamanager.street.entity.SysStreet;
import com.mtx.kyrieboot.system.areamanager.street.service.SysStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysStreetController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:43
 **/
@Controller
@RequestMapping("/street")
public class SysStreetController {

    @Autowired
    private SysStreetService sysStreetService;

    @GetMapping("/list")
    public String index(){
        return "module/street/street";
    }

    @GetMapping("/update")
    public String update(String streetCode, Model model){
        SysStreet sysStreet = sysStreetService.selectByStreetCode(streetCode);
        model.addAttribute("sysStreet",sysStreet);
        return "module/street/updateStreet";
    }


    @GetMapping("/add")
    public String add(){
        return "module/street/addStreet";
    }

}
