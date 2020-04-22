package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysPost;
import com.mtx.kyrieboot.service.SysMenuService;
import com.mtx.kyrieboot.service.SysPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @ClassName SysPostRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 13:26
 **/
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPostRealController {


    @Autowired
    private SysPostService sysPostService;
    @Autowired
    private SysMenuService sysMenuService;

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


    @PostMapping("/addPost")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult addPost(@RequestBody SysPost sysPost){
        JSONObject jsonObject = new JSONObject();
        try{
            SysPost post = sysPostService.findByName(sysPost.getPostName());
            if (post == null){
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


}
