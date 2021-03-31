package com.mtx.kyrieboot.controller.api;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.base.RespCodeEnum;
import com.mtx.kyrieboot.common.TreeCommon;
import com.mtx.kyrieboot.config.exception.BizException;
import com.mtx.kyrieboot.entity.SysDept;
import com.mtx.kyrieboot.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 14:29
 * @Description:
 */
@Slf4j
@Controller
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

    @GetMapping("/update")
    public String update(String deptId, Model model){
        SysDept sysDept = sysDeptService.findByDeptId(deptId);
        model.addAttribute("sysDept",sysDept);
        return "module/dept/updateDept";
    }


    @GetMapping("/getDeptInfo")
    @ResponseBody
    public AjaxResult getDeptInfo(@Param("page")Integer page,@Param("page_size") Integer pageSize){
        log.info("分页查询部门信息列表，请求参数：page:{},pageSize:{}",page,pageSize);
        if(ObjectUtils.isEmpty(page)){
            page = 1;
        }
        if(ObjectUtils.isEmpty(pageSize)){
            pageSize =10;
        }
        AjaxResult ajaxResult = new AjaxResult();
        JSONObject jsonObject = new JSONObject();
        try {
            IPage<SysDept> sysDeptIPage = sysDeptService.getAll(new Page(page,pageSize));
            jsonObject.put("page",sysDeptIPage.getCurrent());
            jsonObject.put("page_size",sysDeptIPage.getSize());
            List<SysDept> sysDepts = buildDeptTree(sysDeptIPage.getRecords());
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
            jsonObject.put("deptTree",sysDepts);
        }catch (Exception e){
            log.info("分页查询部门信息列表,异常信息：{}",e.getMessage());
            throw e;
        }
        ajaxResult.setRespData(jsonObject);
        log.info("分页查询部门信息列表，响应结果：{}", JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }

    @PostMapping("/addDept")
    @ResponseBody
    public AjaxResult addDept(SysDept sysDept){
        log.info("部门信息新增接口，请求参数：{}",JSON.toJSONString(sysDept));
        if(ObjectUtils.isEmpty(sysDept)){
            return AjaxResult.fail("部门信息不能为空");
        }
        AjaxResult ajaxResult = new AjaxResult();
        /*try {
            //获取当前登录用户
            String creatBy = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            sysDept.setCreateBy(creatBy);
            //新增部门
            int result = sysDeptService.addDept(sysDept);
            if(result>0){
                ajaxResult.setRespCode(RespCodeEnum.SUCCESS.getCode());
                ajaxResult.setRespDesc(RespCodeEnum.SUCCESS.getMsg());
                ajaxResult.setRespData(sysDept);
            }else {
                ajaxResult.setRespCode(RespCodeEnum.INTERNAL_SERVER_ERROR.getCode());
                ajaxResult.setRespDesc(RespCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
                ajaxResult.setRespData(sysDept);
            }
        }catch (Exception e){
            log.info("新增部门信息异常结束,异常信息：{}",e.getMessage());
            throw new BizException(e.getMessage());
        }*/
        log.info("部门信息新增接口，响应结果：{}",JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }


    @PostMapping("/updateDept")
    @ResponseBody
    public AjaxResult updateDept(@RequestBody SysDept sysDept){
        log.info("部门信息编辑接口，请求参数：{}",JSON.toJSONString(sysDept));
        AjaxResult ajaxResult = new AjaxResult();
        try {
            //校验当前部门信息是否存在
            SysDept isExist = sysDeptService.findByDeptId(sysDept.getDeptId());
            if(ObjectUtils.isEmpty(isExist)){
                return AjaxResult.fail("部门信息不存在");
            }
            //获取当前登录用户
            String updateBy = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            sysDept.setUpdateBy(updateBy);
            //新增部门
            int result = sysDeptService.updateDeptById(sysDept);
            if(result>0){
                ajaxResult.setRespCode(RespCodeEnum.SUCCESS.getCode());
                ajaxResult.setRespDesc(RespCodeEnum.SUCCESS.getMsg());
                ajaxResult.setRespData(sysDept);
            }else {
                ajaxResult.setRespCode(RespCodeEnum.INTERNAL_SERVER_ERROR.getCode());
                ajaxResult.setRespDesc(RespCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
                ajaxResult.setRespData(sysDept);
            }
        }catch (Exception e){
            log.info("编辑部门信息异常结束,异常信息：{}",e.getMessage());
            throw new BizException(e.getMessage());
        }
        log.info("部门信息编辑接口，响应结果：{}",JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }


    @GetMapping("/deptTree")
    @ResponseBody
    public AjaxResult deptTree(){
        AjaxResult ajaxResult = new AjaxResult();
        log.info("获取组织机构树接口");
        try {
            //获取所有部门列表
            List<SysDept> allDepts = sysDeptService.queryAll(new SysDept());
            //构造部门树
            List<SysDept> deptTree = buildDeptTree(allDepts);
            ajaxResult.setRespCode(RespCodeEnum.SUCCESS.getCode());
            ajaxResult.setRespDesc(RespCodeEnum.SUCCESS.getMsg());
            ajaxResult.setRespData(deptTree);
        }catch (Exception e){
            log.info("获取部门组织机构树异常，异常信息：{}",e.getMessage());
            throw new BizException(e.getMessage());
        }
        log.info("获取部门组织机构树，响应结果：{}",JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }

    public List<SysDept> buildDeptTree(List<SysDept> sysDepts) {
        List<SysDept> tree = new ArrayList<>();
        for (Iterator<SysDept> it = sysDepts.iterator(); it.hasNext(); ) {
            SysDept sysDept = (SysDept) it.next();
            if (sysDept.getParentId().equals("0")) {
                TreeCommon.recursionFn(sysDepts, sysDept);
                tree.add(sysDept);
            }
        }
        return tree;
    }


    @GetMapping("/deptList")
    @ResponseBody
    public AjaxResult getDeptInfo(String deptName){
        log.info("查询部门信息，请求参数：deptName:{}",deptName);
        SysDept sysDept = new SysDept();
        sysDept.setDeptName(deptName);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<SysDept> sysDepts = sysDeptService.queryAll(sysDept);
            ajaxResult.setRespCode(RespCodeEnum.SUCCESS.getCode());
            ajaxResult.setRespDesc(RespCodeEnum.SUCCESS.getMsg());
            ajaxResult.setRespData(sysDepts);
        }catch (Exception e){
            log.info("查询部门信息,异常信息：{}",e.getMessage());
            throw e;
        }
        log.info("查询部门信息，响应结果：{}", JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }





}
