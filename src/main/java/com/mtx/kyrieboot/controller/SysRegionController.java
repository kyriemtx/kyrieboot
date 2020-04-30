package com.mtx.kyrieboot.controller;

import com.mtx.kyrieboot.entity.SysRegion;
import com.mtx.kyrieboot.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName SysRegionController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 14:33
 **/
@Controller
@RequestMapping("/region")
public class SysRegionController {

    @Autowired
    private SysRegionService sysRegionService;

    @GetMapping("/list")
    public String index(){
        return "module/region/region";
    }

    @GetMapping("/update")
    public String update(int id, Model model){
        SysRegion sysRegion = sysRegionService.selectRegionById(id);
        model.addAttribute("sysRegion",sysRegion);
        return "module/region/updateRegion";
    }

    @GetMapping("/add")
    public String add(){
        return "module/region/addRegion";
    }
}
