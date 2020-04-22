package com.mtx.kyrieboot.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysPostController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 13:23
 **/
@Controller
@RequestMapping("/post")
public class SysPostController {

    @GetMapping("/list")
    public String index(){
        return "module/post/post";
    }

    @GetMapping("/update")
    public String update(String name, String authority, String id, Model model){
       /* model.addAttribute("name",name);
        model.addAttribute("authority",authority);
        model.addAttribute("id",id);*/
        return "module/role/updateRole";
    }

    @GetMapping("/add")
    public String add(){
        return "module/post/addPost";
    }

}
