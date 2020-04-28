package com.mtx.kyrieboot.controller;

import com.mtx.kyrieboot.entity.SysProvince;
import com.mtx.kyrieboot.service.SysProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ProvinceController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 11:38
 **/
@Controller
@RequestMapping("/province")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProvinceController {

    @Autowired
    private SysProvinceService sysProvinceService;

    @GetMapping("/list")
    public String index(){
        return "module/province/province";
    }

    @GetMapping("/update")
    public String update(String provinceCode, Model model){
        if(provinceCode != null || !provinceCode.equals("")){
            SysProvince sysProvince = sysProvinceService.findByCode(provinceCode);
            model.addAttribute("sysProvince",sysProvince);
        }
        return "module/province/updateProvince";
    }

    @GetMapping("/add")
    public String add(){
        return "module/province/addProvince";
    }



    @GetMapping("/liandong")
    public String liandong(){
        return "module/areamanager/index";
    }

}
