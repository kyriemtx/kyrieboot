package com.mtx.kyrieboot.controller.real;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysArea;
import com.mtx.kyrieboot.service.SysAreaService;
import com.mtx.kyrieboot.service.SysCityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysAreaRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 16:58
 **/
@RestController
@Slf4j
@RequestMapping("/area")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysAreaRealController {


    @Autowired
    private SysAreaService sysAreaService;
    @Autowired
    private SysCityService sysCityService;


    @GetMapping("/getAreaInfo")
    public AjaxResult getAreaInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();
        IPage<SysArea> sysAreaIPage = sysAreaService.getAll(new Page(page,pageSize));
        jsonObject.put("total",sysAreaIPage.getTotal());
        jsonObject.put("page",sysAreaIPage.getCurrent());
        jsonObject.put("page_size",sysAreaIPage.getPages());
        List<SysArea> sysAreas = sysAreaIPage.getRecords();
        if(sysAreas.size()>0){
            for(SysArea sysArea : sysAreas){
                 String cityName = sysCityService.selectNameByCode(sysArea.getCityCode());
                 sysArea.setCityName(cityName);
                if(sysArea.getDataState().equals("0")){
                    sysArea.setDataState("正常");
                }
                if(sysArea.getDataState().equals("1")){
                    sysArea.setDataState("停用");
                }
            }
        }
        jsonObject.put("sysAreaList",sysAreas);
        return AjaxResult.success(jsonObject);

    }


    @ResponseBody
    @PostMapping("/addArea")
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public AjaxResult addArea(@RequestBody SysArea sysArea){
        JSONObject jsonObject = new JSONObject();
        try {
            SysArea area = sysAreaService.selectByCode(sysArea.getAreaCode());
            if(area == null){
                sysAreaService.insertSysArea(sysArea);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @ResponseBody
    @PostMapping("/updateArea")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult updateArea(@RequestBody SysArea sysArea){
        JSONObject jsonObject = new JSONObject();
        try {
            SysArea area = sysAreaService.selectByCode(sysArea.getAreaCode());
            if(area != null){
                sysAreaService.updateSysArea(sysArea);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }



    @ResponseBody
    @GetMapping("/deleteArea")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult deleteArea(String areaCode){
        JSONObject jsonObject = new JSONObject();
        try {
            SysArea sysArea = sysAreaService.selectByCode(areaCode);
            if(sysArea == null){
                jsonObject.put("code",501);
            }else {
                sysAreaService.deleteByAreaCode(areaCode);
                jsonObject.put("code",200);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

    @ResponseBody
    @GetMapping("/selectByCityCode")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult selectByCityCode(String cityCode){
        JSONObject jsonObject = new JSONObject();
        try {
            if(cityCode != null){
                List<SysArea> sysAreas = sysAreaService.selectAreasByCityCode(cityCode);
                jsonObject.put("data",sysAreas);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",500);
            }
        }catch (Exception e) {
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


}
