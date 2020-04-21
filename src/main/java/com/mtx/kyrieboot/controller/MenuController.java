package com.mtx.kyrieboot.controller;

import com.mtx.kyrieboot.entity.SysMenu;
import com.mtx.kyrieboot.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MenuController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:03
 **/
@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("/list")
    public String index(){
        return "module/menu/menu";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        SysMenu sysMenu = sysMenuService.getById(id);
        model.addAttribute("sysMenu",sysMenu);
        return "module/menu/updateMenu";
    }

    @GetMapping("/add")
    public String add(){
        return "module/menu/addMenu";
    }
}
