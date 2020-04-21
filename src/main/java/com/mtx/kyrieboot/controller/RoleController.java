package com.mtx.kyrieboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName RoleController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:04
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/list")
    public String index(){
        return "module/role/role";
    }

    @GetMapping("/update")
    public String update(String name, String authority, String id, Model model){
        model.addAttribute("name",name);
        model.addAttribute("authority",authority);
        model.addAttribute("id",id);
        return "module/role/updateRole";
    }

    @GetMapping("/add")
    public String add(){
        return "module/role/addRole";
    }
}
