package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysRegion;
import com.mtx.kyrieboot.service.SysRegionService;
import com.mtx.kyrieboot.vo.SysRegionListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysRegionRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 14:39
 **/
@RestController
@RequestMapping("/region")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRegionRealController {

    @Autowired
    private SysRegionService sysRegionService;

    @GetMapping("/getRegionInfo")
    public AjaxResult getRegionInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        List<SysRegionListVO> sysRegionListVOS = new ArrayList<>();
        IPage<SysRegion> level0 = sysRegionService.topLevel(new Page(page,pageSize));
        //组装数据
        List<SysRegion> regions = level0.getRecords();
        for(SysRegion sysRegion:regions){
            SysRegionListVO regionListVO = sysRegionService.selectRegions(sysRegion.getId());
            sysRegionListVOS.add(regionListVO);
        }
        jsonObject.put("total",level0.getTotal());
        jsonObject.put("page",level0.getCurrent());
        jsonObject.put("page_size",level0.getSize());
        jsonObject.put("regionList",sysRegionListVOS);
        return AjaxResult.success(jsonObject);
    }

}
