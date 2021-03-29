package com.mtx.kyrieboot.controller.api;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysDept;
import com.mtx.kyrieboot.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 14:29
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDeptController {

    @Autowired
    SysDeptService sysDeptService;

    @GetMapping("/list")
    public String index() {
        return "module/dept/dept";
    }
    @GetMapping("/add")
    public String add(){
        return "module/dept/addDept";
    }


    @GetMapping("/getDeptInfo")
    public AjaxResult getDeptInfo(@Param("page")int page,@Param("pageSize") int pageSize){
        log.info("分页查询部门信息列表，请求参数：page:{},pageSize:{}",page,pageSize);
        AjaxResult ajaxResult = new AjaxResult();
        JSONObject jsonObject = new JSONObject();
        try {
            IPage<SysDept> sysDeptIPage = sysDeptService.getAll(new Page(page,pageSize));
            jsonObject.put("page",sysDeptIPage.getCurrent());
            jsonObject.put("page_size",sysDeptIPage.getSize());
            List<SysDept> sysDepts = sysDeptIPage.getRecords();
            if(sysDepts.size()>0){
                for(SysDept sysDept : sysDepts){
                    if(sysDept.getStatus().equals("0")){
                        sysDept.setStatus("正常");
                    }else {
                        sysDept.setStatus("停用");
                    }
                    if(sysDept.getDelFlag().equals("2")){
                        sysDept.setDelFlag("已删除");
                    }else {
                        sysDept.setDelFlag("正常");

                    }
                }
            }
            jsonObject.put("depts",sysDepts);
        }catch (Exception e){
            log.info("分页查询部门信息列表,异常信息：{}",e.getMessage());
            throw e;
        }
        log.info("分页查询部门信息列表，响应结果：{}", JSON.toJSONString(AjaxResult.success(jsonObject)));
        return ajaxResult;

    }




}
