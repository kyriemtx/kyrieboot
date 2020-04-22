package com.mtx.kyrieboot.controller;

import com.mtx.kyrieboot.entity.SysPost;
import com.mtx.kyrieboot.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysPostService sysPostService;

    @GetMapping("/list")
    public String index(){
        return "module/post/post";
    }

    @GetMapping("/update")
    public String update(String postId, Model model){
        if(postId != null || !postId.equals("")){
            SysPost sysPost = sysPostService.selectById(postId);
            model.addAttribute("sysPost",sysPost);
        }
        return "module/post/updatePost";
    }

    @GetMapping("/add")
    public String add(){
        return "module/post/addPost";
    }

}
