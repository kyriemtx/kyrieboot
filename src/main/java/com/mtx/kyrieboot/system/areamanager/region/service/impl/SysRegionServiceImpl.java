package com.mtx.kyrieboot.system.areamanager.region.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.areamanager.region.entity.SysRegion;
import com.mtx.kyrieboot.system.areamanager.region.mapper.SysRegionMapper;
import com.mtx.kyrieboot.system.areamanager.region.service.SysRegionService;
import com.mtx.kyrieboot.utils.RegionEnum;
import com.mtx.kyrieboot.vo.SysRegionListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysRegionServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/30 12:01
 **/
@Service
@Slf4j
public class SysRegionServiceImpl implements SysRegionService {

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Override
    public SysRegionListVO selectRegions(int id) {
        log.info("[根据区域代码查询该区域及其子区域信息接口],请求参数：区域代码 id:{}",id);
        SysRegion sysRegion = selectRegionById(id);
        SysRegionListVO sysRegionListVO = new SysRegionListVO();
        List<SysRegion> firestLevels = new ArrayList<>();
        List<SysRegion> secondLevels = new ArrayList<>();
        List<SysRegion> thirdLevels = new ArrayList<>();
        List sysRegions  = new ArrayList();
        if(sysRegion != null){
            sysRegionListVO = sysRegionToListVO(sysRegion);
            if(sysRegion.getLevel() == RegionEnum.LEVEL_0.getLevel()){
                firestLevels = selectFirstLevel(sysRegion);
                secondLevels = selectSecondLevel(firestLevels);
                thirdLevels = selectThirdLevel(secondLevels);
                sysRegions.add(firestLevels);
                sysRegions.add(secondLevels);
                sysRegions.add(thirdLevels);
                sysRegionListVO.setChildren(sysRegions);

            }else if(sysRegion.getLevel() == RegionEnum.LEVEL_1.getLevel()){
                List<SysRegion> level1 = new ArrayList<>();
                level1.add(sysRegion);
                secondLevels = selectSecondLevel(level1);
                thirdLevels = selectThirdLevel(secondLevels);
                sysRegions.add(secondLevels);
                sysRegions.add(thirdLevels);
                sysRegionListVO.setChildren(sysRegions);
            }else if(sysRegion.getLevel() == RegionEnum.LEVEL_2.getLevel()){
                List<SysRegion> level2 = new ArrayList<>();
                level2.add(sysRegion);
                thirdLevels = selectThirdLevel(level2);
                sysRegions.add(thirdLevels);
                sysRegionListVO.setChildren(sysRegions);
            }
        }
        return sysRegionListVO;
    }

    @Override
    public List<SysRegion> selectByPid(int pid) {
        log.info("[根据区域代码查询当前区域下面的所有子区域],请求参数 pid:{}",pid);
        List<SysRegion> sysRegions = sysRegionMapper.selectRegionByPid(pid);
        log.info("[根据区域代码查询当前区域下面的所有子区域],返回参数：{}", JSON.toJSONString(sysRegions));
        return sysRegions;
    }



    @Override
    public List<SysRegion> selectFirstLevel(SysRegion sysRegion){
        log.info("[组装一级区域信息列表接口],请求参数 :{}",JSON.toJSONString(sysRegion));
        List<SysRegion> sysRegions = sysRegionMapper.selectRegionByPid(sysRegion.getId());
        log.info("[组装一级区域信息列表接口],返回参数：{}", JSON.toJSONString(sysRegions));
        return sysRegions;
    }


    /**
     * 组装二级区域信息
     * @param sysRegions
     * @return
     */
    @Override
    public List<SysRegion> selectSecondLevel(List<SysRegion> sysRegions){
        log.info("[组装二级区域信息列表接口],请求参数 :{}",JSON.toJSONString(sysRegions));
        List<SysRegion> secondLevels = new ArrayList<>();
        for(SysRegion sysRegion:sysRegions){
            List<SysRegion> secondsSysRegions = sysRegionMapper.selectRegionByPid(sysRegion.getId());
            secondLevels.addAll(secondsSysRegions);
        }
        log.info("[组装二级区域信息列表接口],返回参数：{}", JSON.toJSONString(secondLevels));
        return secondLevels;
    }


    /**
     * 组装三级区域信息
     * @param sysRegions
     * @return
     */
    @Override
    public List<SysRegion> selectThirdLevel(List<SysRegion> sysRegions){
        log.info("[组装三级区域信息列表接口],请求参数 :{}",JSON.toJSONString(sysRegions));
        List<SysRegion> thirdLevels = new ArrayList<>();
        for(SysRegion sysRegion: sysRegions){
            List<SysRegion> thirdSysRegions = sysRegionMapper.selectRegionByPid(sysRegion.getId());
            thirdLevels.addAll(thirdSysRegions);
        }
        log.info("[组装三级区域信息列表接口],返回参数：{}", JSON.toJSONString(thirdLevels));
        return thirdLevels;
    }

    /**
     * 根据区域代码查询区域信息
     * @param id
     * @return
     */
    @Override
    public SysRegion selectRegionById(int id){
        log.info("[根据区域代码查询区域信息接口],请求参数：区域代码 id:{}",id);
        SysRegion sysRegion = sysRegionMapper.selectRegionById(id);
        log.info("[根据区域代码查询区域信息接口],返回参数：{}",JSON.toJSONString(sysRegion));
        return sysRegion;
    }


    @Override
    public IPage<SysRegion> topLevel(Page page) {
        log.info("查询顶级区域信息接口");
        return sysRegionMapper.topLevel(page);
    }

    /**
     * SysRegion转SysRegionListVO
     * @param sysRegion
     * @return
     */
    public SysRegionListVO sysRegionToListVO(SysRegion sysRegion){
        SysRegionListVO sysRegionListVO = new SysRegionListVO();
        sysRegionListVO.setId(sysRegion.getId());
        sysRegionListVO.setName(sysRegion.getName());
        sysRegionListVO.setSname(sysRegion.getSname());
        sysRegionListVO.setLevel(sysRegion.getLevel());
        sysRegionListVO.setCityCode(sysRegion.getCityCode());
        sysRegionListVO.setPostCode(sysRegion.getPostCode());
        sysRegionListVO.setMerName(sysRegion.getMerName());
        sysRegionListVO.setLat(sysRegion.getLat());
        sysRegionListVO.setLng(sysRegion.getLng());
        sysRegionListVO.setGmtCreate(sysRegion.getGmtCreate());
        sysRegionListVO.setStatus(sysRegion.getStatus());
        return sysRegionListVO;
    }

}
