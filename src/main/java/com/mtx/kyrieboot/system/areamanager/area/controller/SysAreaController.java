package com.mtx.kyrieboot.system.areamanager.area.controller;

import com.mtx.kyrieboot.system.areamanager.area.entity.SysArea;
import com.mtx.kyrieboot.system.areamanager.area.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysAreaControll
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:52
 **/
@Controller
@RequestMapping("/area")
public class SysAreaController {


    @Autowired
    private SysAreaService sysAreaService;

    @GetMapping("/list")
    public String index(){
        return "module/area/area";
    }


    @GetMapping("/update")
    public String update(String areaCode, Model model) {
        SysArea sysArea = sysAreaService.selectByCode(areaCode);
        model.addAttribute("sysArea", sysArea);
        return "module/area/updateArea";
    }

    @GetMapping("/add")
    public String add(){
        return "module/area/addArea";
    }

}
