package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysCity;
import com.mtx.kyrieboot.service.SysCityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysCityRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 13:08
 **/
@RestController
@Slf4j
@RequestMapping("/city")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysCityRealController {

    @Autowired
    private SysCityService sysCityService;

    @GetMapping("/getCityInfo")
    public AjaxResult getCityInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysCity> sysCityIPage = sysCityService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysCityIPage.getTotal());
        jsonObject.put("page",sysCityIPage.getCurrent());
        jsonObject.put("page_size",sysCityIPage.getSize());
        List<SysCity> sysCities = sysCityIPage.getRecords();
        if(sysCities.size()>0){
            for(SysCity sysCity : sysCities){
                if(sysCity.getDataState().equals("0")){
                    sysCity.setDataState("正常");
                }
                if(sysCity.getDataState().equals("1")){
                    sysCity.setDataState("停用");
                }
            }
        }
        jsonObject.put("sysCityList",sysCities);

        return AjaxResult.success(jsonObject);
    }


    @PostMapping("/addCity")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public AjaxResult addCity(@RequestBody SysCity sysCity){
        JSONObject jsonObject = new JSONObject();
        try {
            SysCity city = sysCityService.selectByCode(sysCity.getCityCode());
            if(city == null){
                sysCityService.inserSysCity(sysCity);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @PostMapping("/updateCity")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult updateCity(@RequestBody SysCity sysCity) {
        JSONObject jsonObject = new JSONObject();
        try {
            SysCity city = sysCityService.selectByCode(sysCity.getCityCode());
            if(city != null){
                sysCityService.updataSysCity(sysCity);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @GetMapping("/deleteCity")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult deleteCity(String cityCode){
        JSONObject jsonObject = new JSONObject();
        try {
            SysCity sysCity = sysCityService.selectByCode(cityCode);
            if(sysCity != null){
                sysCityService.deleteByCode(cityCode);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

}
