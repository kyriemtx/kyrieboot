package com.mtx.kyrieboot.system.post.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.system.post.entity.SysPost;
import com.mtx.kyrieboot.system.post.service.SysPostService;
import com.mtx.kyrieboot.system.post.service.SysPostService;
import com.mtx.kyrieboot.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysPostRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 13:26
 **/
@RestController
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPostRealController {


    @Autowired
    private SysPostService sysPostService;

    @GetMapping("/getPostInfo")
    public AjaxResult getRoleInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysPost> sysPostIPage = sysPostService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysPostIPage.getTotal());
        jsonObject.put("page",sysPostIPage.getCurrent());
        jsonObject.put("page_size",sysPostIPage.getSize());
        List<SysPost> sysPosts = sysPostIPage.getRecords();
        if(sysPosts.size()>0){
            for(SysPost sysPost : sysPosts){
                if(sysPost.getPostStatus().equals("0")){
                    sysPost.setPostStatus("正常");
                }
                if(sysPost.getPostStatus().equals("1")){
                    sysPost.setPostStatus("停用");
                }
            }
        }
        jsonObject.put("sysPostList",sysPosts);

        return AjaxResult.success(jsonObject);
    }

    @ResponseBody
    @GetMapping("/selectForm")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult selectForm(SysPost sysPost){
        JSONObject jsonObject = new JSONObject();
        List<SysPost> sysPosts = new ArrayList<>();
        if(sysPost == null){
            sysPosts = sysPostService.getAll();
        }else {
            sysPosts = sysPostService.selectForm(sysPost);
        }
        jsonObject.put("sysPostList",sysPosts);
        return AjaxResult.success(jsonObject);

    }

    @PostMapping("/addPost")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult addPost(@RequestBody SysPost sysPost){
        JSONObject jsonObject = new JSONObject();
        try{
            SysPost post = sysPostService.findByName(sysPost.getPostName());
            if (post == null){
                String postId = UUIDUtils.getUUID();
                sysPost.setPostId(postId);
                sysPostService.insertSysPost(sysPost);
                jsonObject.put("code",200);
            } else {
                // 501 岗位已存在
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/updatePost")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateRole(@RequestBody SysPost sysPost){
        JSONObject jsonObject = new JSONObject();
        try {
            SysPost post = sysPostService.findByName(sysPost.getPostName());
            if(post != null){
                sysPostService.updateSysPost(sysPost);
                jsonObject.put("code",200);
            }else {
                log.info("【修改岗位信息接口】，当前岗位信息不存在");
                jsonObject.put("code", 500);
            }
        } catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }


    @GetMapping("/getPost")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult selectByPostId(String postId){
        JSONObject jsonObject = new JSONObject();
        try {
            if(postId != null || !postId.equals("")){
                SysPost sysPost = sysPostService.selectById(postId);
                jsonObject.put("data",sysPost);
                jsonObject.put("code",200);
            }
        }catch (Exception e){
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }


    @GetMapping("/deletePost")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateSysPost(String postId){
        JSONObject jsonObject = new JSONObject();
        try {
            if(postId != null || !postId.equals("")){
                int res = sysPostService.deleteSysPost(postId);
                jsonObject.put("result",res);
                jsonObject.put("code",200);
            }
        }catch (Exception e){
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }



}
