package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysProvince;
import com.mtx.kyrieboot.service.SysProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysProvinceRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 12:05
 **/
@RestController
@Slf4j
@RequestMapping("/province")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysProvinceRealController {

    @Autowired
    private SysProvinceService sysProvinceService;


    @GetMapping("/getProvinceInfo")
    public AjaxResult getProvinceInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysProvince> sysProvinceIPage = sysProvinceService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysProvinceIPage.getTotal());
        jsonObject.put("page",sysProvinceIPage.getCurrent());
        jsonObject.put("page_size",sysProvinceIPage.getSize());
        List<SysProvince> sysProvinces = sysProvinceIPage.getRecords();
        if(sysProvinces.size()>0){
            for(SysProvince sysProvince : sysProvinces){
                if(sysProvince.getDataState().equals("0")){
                    sysProvince.setDataState("正常");
                }
                if(sysProvince.getDataState().equals("1")){
                    sysProvince.setDataState("停用");
                }
            }
        }
        jsonObject.put("sysProvinceList",sysProvinces);

        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/addProvince")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult addProvince(@RequestBody SysProvince sysProvince){
        JSONObject jsonObject = new JSONObject();
        try {
            SysProvince province = sysProvinceService.findByCode(sysProvince.getProvinceCode());
            if(province == null){
                sysProvinceService.insertSysProvince(sysProvince);
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code","501");
            }
        }catch (Exception e){
            jsonObject.put("code","500");
            e.printStackTrace();
        }
        return AjaxResult.success(jsonObject);
    }


    @PostMapping("/updateProvince")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateProvince(@RequestBody SysProvince sysProvince){
        JSONObject jsonObject = new JSONObject();
        try {
            SysProvince province = sysProvinceService.findByCode(sysProvince.getProvinceCode());
            if(province != null){
                int res = sysProvinceService.updateSysProvince(sysProvince);
                jsonObject.put("code",200);
            }else {
                log.info("数据库未查到当前省份信息");
                jsonObject.put("code",500);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getProvince")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult getProvinceInfo(int id){
        JSONObject jsonObject = new JSONObject();
        try {
           SysProvince sysProvince = sysProvinceService.selectById(id);
           jsonObject.put("data",sysProvince);
           jsonObject.put("code",200);
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/deleteProvince")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult  deleteProvince(int id){
        JSONObject jsonObject = new JSONObject();
        try{
            SysProvince province = sysProvinceService.selectById(id);
            if(province != null){
                int res = sysProvinceService.deleteSysProvince(id);
                jsonObject.put("code",200);
            }else {
                log.info("数据库未查到当前省份信息");
                jsonObject.put("code",500);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }


    @GetMapping("/citySelect")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult selectProvincesCodes(){
        JSONObject jsonObject = new JSONObject();
        try {
            List<SysProvince> sysProvinces = sysProvinceService.citySelect();
            jsonObject.put("code",200);
            jsonObject.put("data",sysProvinces);
        }catch (Exception e){
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

}
