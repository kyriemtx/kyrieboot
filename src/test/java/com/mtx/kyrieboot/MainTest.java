package com.mtx.kyrieboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysPost;
import com.mtx.kyrieboot.entity.SysRole;
import com.mtx.kyrieboot.service.SysPostService;
import com.mtx.kyrieboot.service.SysRoleService;
import com.mtx.kyrieboot.vo.SysRegionListVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MainTest
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 12:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MainTest {


    @Autowired
    private SysPostService sysPostService;
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void 岗位信息(){
        Page page = new Page();
        IPage<SysPost> sysPostIPage = sysPostService.getAll(page);
        log.info("查询结果：{}",JSON.toJSONString(sysPostIPage));
    }


    @Test
    public void 角色信息(){
        Page page = new Page();
        IPage<SysRole> sysRoleIPage = sysRoleService.getAll(page);
        long size = sysRoleIPage.getSize();
    }


   /* public void 查询区域信息() {
        int id = 100000;
        SysRegionListVO sysRegionListVO = new SysRegionListVO();
        sysRegionListVO = sysRegionService.selectRegions(id);
        System.err.println(JSON.toJSONString(sysRegionListVO));
        log.info("查询结果：{}",JSON.toJSONString(sysRegionListVO));
    }*/
}
