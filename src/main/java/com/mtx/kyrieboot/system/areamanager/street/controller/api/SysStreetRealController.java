package com.mtx.kyrieboot.system.areamanager.street.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.system.areamanager.area.entity.SysArea;
import com.mtx.kyrieboot.system.areamanager.area.service.SysAreaService;
import com.mtx.kyrieboot.system.areamanager.city.entity.SysCity;
import com.mtx.kyrieboot.system.areamanager.city.service.SysCityService;
import com.mtx.kyrieboot.system.areamanager.province.service.SysProvinceService;
import com.mtx.kyrieboot.system.areamanager.street.entity.SysStreet;
import com.mtx.kyrieboot.system.areamanager.street.service.SysStreetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysStreetRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/29 13:47
 **/
@RestController
@Slf4j
@RequestMapping("/street")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysStreetRealController {

    @Autowired
    private SysAreaService sysAreaService;
    @Autowired
    private SysStreetService sysStreetService;
    @Autowired
    private SysCityService sysCityService;
    @Autowired
    private SysProvinceService sysProvinceService;

    @GetMapping("/getStreetInfo")
    public AjaxResult getStreetinfos(@RequestParam("page") int page,
                                     @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();
        IPage<SysStreet> sysStreetIPage = sysStreetService.getAll(new Page(page,pageSize));
        jsonObject.put("total",sysStreetIPage.getTotal());
        jsonObject.put("page",sysStreetIPage.getCurrent());
        jsonObject.put("page_size",sysStreetIPage.getPages());
        List<SysStreet> sysStreets = sysStreetIPage.getRecords();
        if(sysStreets.size()>0){
            for(SysStreet sysStreet: sysStreets){
                SysArea sysArea = sysAreaService.selectByCode(sysStreet.getAreaCode());
                SysCity sysCity =sysCityService.selectByCode(sysArea.getCityCode());
                String provinceName = sysProvinceService.selectProvinceNameByCode(sysCity.getProvinceCode());
                String preName = provinceName+sysCity.getCityName()+sysArea.getAreaName();
                sysStreet.setAreaName(preName);
            }
        }
        jsonObject.put("sysStreetList",sysStreets);
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/selectForm")
    @ResponseBody
    public AjaxResult selectForm(SysStreet sysStreet){
        JSONObject jsonObject = new JSONObject();
        if(sysStreet.getAreaName().equals("") && sysStreet.getStreetName().equals("") && sysStreet.getDataState().equals("")){
            IPage<SysStreet> sysStreetIPage = sysStreetService.getAll(new Page(1,10));
            jsonObject.put("total",sysStreetIPage.getTotal());
            jsonObject.put("page",sysStreetIPage.getCurrent());
            jsonObject.put("page_size",sysStreetIPage.getPages());
            List<SysStreet> sysStreets = sysStreetIPage.getRecords();
            if(sysStreets.size()>0){
                for(SysStreet street: sysStreets){
                    SysArea sysArea = sysAreaService.selectByCode(street.getAreaCode());
                    SysCity sysCity =sysCityService.selectByCode(sysArea.getCityCode());
                    String provinceName = sysProvinceService.selectProvinceNameByCode(sysCity.getProvinceCode());
                    String preName = provinceName+sysCity.getCityName()+sysArea.getAreaName();
                    street.setAreaName(preName);
                }
            }
            jsonObject.put("sysStreetList",sysStreets);
            return AjaxResult.success(jsonObject);
        }else {
            List<SysStreet> sysStreets = sysStreetService.selectForm(sysStreet);
            if(sysStreets.size()>0){
                for(SysStreet street: sysStreets){
                    SysArea sysArea = sysAreaService.selectByCode(street.getAreaCode());
                    SysCity sysCity =sysCityService.selectByCode(sysArea.getCityCode());
                    String provinceName = sysProvinceService.selectProvinceNameByCode(sysCity.getProvinceCode());
                    String preName = provinceName+sysCity.getCityName()+sysArea.getAreaName();
                    street.setAreaName(preName);
                }
            }
            jsonObject.put("sysStreetList",sysStreets);
            return AjaxResult.success(jsonObject);
        }
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class,})
    public AjaxResult addStreet(@RequestBody SysStreet sysStreet){
        JSONObject jsonObject = new JSONObject();
        try {
            SysStreet street = sysStreetService.selectByStreetCode(sysStreet.getStreetCode());
            if(street == null){
                sysStreetService.insertSysStreet(sysStreet);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @PostMapping("/update")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class,})
    public AjaxResult updateStreet(@RequestBody SysStreet sysStreet){
        JSONObject jsonObject = new JSONObject();
        try {
            SysStreet street = sysStreetService.selectByStreetCode(sysStreet.getStreetCode());
            if(street != null){
                sysStreetService.updateSysStreet(sysStreet);
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
    @GetMapping("/deleteStreet")
    public AjaxResult deleteStreet(String streetCode){
        JSONObject jsonObject = new JSONObject();
        try {
            SysStreet sysStreet = sysStreetService.selectByStreetCode(streetCode);
            if(sysStreet == null){
                jsonObject.put("code",501);
            }else {
                sysStreetService.deleteByStreetCode(streetCode);
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @ResponseBody
    @GetMapping("/selectByAreaCode")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult selectByAreaCode(String areaCode){
        JSONObject jsonObject = new JSONObject();
        try {
            if(areaCode != null){
                List<SysStreet> sysStreets = sysStreetService.selectByAreaCode(areaCode);
                jsonObject.put("code",200);
                jsonObject.put("data",sysStreets);
            }else {
                jsonObject.put("code",500);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

}
