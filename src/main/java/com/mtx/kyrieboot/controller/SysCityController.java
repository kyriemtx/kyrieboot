package com.mtx.kyrieboot.controller;

import com.mtx.kyrieboot.entity.SysCity;
import com.mtx.kyrieboot.entity.SysProvince;
import com.mtx.kyrieboot.service.SysCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysCityController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 13:04
 **/
@Controller
@RequestMapping("/city")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysCityController {


    @Autowired
    private SysCityService sysCityService;

    @GetMapping("/list")
    public String index(){
        return "module/city/city";
    }


    @GetMapping("/update")
    public String update(int id, Model model){
        SysCity sysCity = sysCityService.selectById(id);
        model.addAttribute("sysCity",sysCity);
        return "module/city/updateCity";
    }

    @GetMapping("/add")
    public String add(){
        return "module/city/addCity";
    }


}
