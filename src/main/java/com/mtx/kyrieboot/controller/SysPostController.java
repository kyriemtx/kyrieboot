package com.mtx.kyrieboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String update(Integer postId, Model model){
        model.addAttribute("id",postId);
        return "module/post/updatePost";
    }

    @GetMapping("/add")
    public String add(){
        return "module/post/addPost";
    }

}
