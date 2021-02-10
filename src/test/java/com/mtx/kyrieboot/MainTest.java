package com.mtx.kyrieboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.CommonConstants;
import com.mtx.kyrieboot.common.KeyConstants;
import com.mtx.kyrieboot.kyriegen.entity.TableEntity;
import com.mtx.kyrieboot.kyriegen.service.KyrieGenService;
import com.mtx.kyrieboot.sequence.SequenceClient;
import com.mtx.kyrieboot.system.post.entity.SysPost;
import com.mtx.kyrieboot.system.post.service.SysPostService;
import com.mtx.kyrieboot.system.role.entity.SysRole;
import com.mtx.kyrieboot.system.role.service.SysRoleService;
import com.mtx.kyrieboot.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    @Autowired
    private KyrieGenService kyrieGenService;
    @Autowired
    SequenceClient sequenceClient;

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


    @Test
    public void 查询表列表(){
        IPage<TableEntity> iPage =kyrieGenService.kyrieQueryTableList(new Page(1,10));
        List<TableEntity> tableEntities = iPage.getRecords();

        String tableName = "sys_area";
        TableEntity tableEntity  = kyrieGenService.selectByTableName(tableName);
        String name = tableEntity.getTableName();
    }

    @Test
    public void 唯一序列验证(){
        //商户费率模板号要求  TS+当前日期YYYYMMDD+6位序列号  如TS20200610000001
        String prefix = CommonConstants.TEMP_ID_SEQ_PREFIX+DateUtil.getCurrentDate();
        String tempId = sequenceClient.getSeqNo(KeyConstants.TEMP_ID_SEQ,prefix,6);
        System.err.println("运行结果："+tempId);
    }

}
