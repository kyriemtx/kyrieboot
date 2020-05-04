package com.mtx.kyrieboot.system.notice.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.system.notice.entity.SysNotice;
import com.mtx.kyrieboot.system.notice.mapper.SysNoticeMapper;
import com.mtx.kyrieboot.system.notice.service.SysNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysNoticeServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/2 10:13
 **/
@Service
@Slf4j
public class SysNoticeServiceImpl implements SysNoticeService {


    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @Override
    public SysNotice selectByPrimaryKey(int id) {
        log.info("[根据主键查询公告信息接口]，请求参数：{}",id);
        SysNotice sysNotice = sysNoticeMapper.selectByKeyId(id);
        log.info("[根据主键查询公告信息接口]，返回参数：{}", JSON.toJSONString(sysNotice));
        return sysNotice;
    }

    @Override
    public List<SysNotice> selectByNotice(SysNotice sysNotice) {
        log.info("[根据表单查询条件查询公告信息接口]，请求参数：{}",JSON.toJSONString(sysNotice));
        List<SysNotice> sysNotices = sysNoticeMapper.selectByNotice(sysNotice);
        log.info("[根据表单查询条件查询公告信息接口]，返回参数：{}", JSON.toJSONString(sysNotices));
        return sysNotices;
    }

    @Override
    public IPage<SysNotice> getAll(Page page) {
        log.info("[分页查询公告信息接口]，请求参数：{}",JSON.toJSONString(page));
        IPage<SysNotice> noticeIPage = sysNoticeMapper.getAll(page);
        log.info("[分页查询公告信息接口]，返回参数：{}",JSON.toJSONString(noticeIPage));
        return noticeIPage;
    }

    @Override
    public int insertSysNotice(SysNotice sysNotice) {
        log.info("[新增公告信息接口]，请求参数：{}",JSON.toJSONString(sysNotice));
        int res = sysNoticeMapper.insert(sysNotice);
        log.info("[新增公告信息接口]，返回参数：{}",JSON.toJSONString(res));
        return res;
    }

    @Override
    public int updateSysNotice(SysNotice sysNotice) {
        log.info("[修改公告信息接口]，请求参数：{}",JSON.toJSONString(sysNotice));
        int res = sysNoticeMapper.updateById(sysNotice);
        log.info("[修改公告信息接口]，返回参数：{}",JSON.toJSONString(res));
        return res;
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        log.info("[删除公告信息接口]，请求参数：{}",id);
        int res = sysNoticeMapper.deleteByKeyId(id);
        log.info("[删除公告信息接口]，返回参数：{}",res);
        return res;
    }


    @Override
    public List<SysNotice> getAll() {
        return sysNoticeMapper.getAll();
    }

    @Override
    public List<SysNotice> selectForm(SysNotice sysNotice) {
        List<SysNotice> sysNotices = new ArrayList<>();
        if(sysNotice.getNoticeType() == null || sysNotice.getNoticeType().equals("")){
            if(sysNotice.getStatus() == null || sysNotice.getStatus().equals("")){
                sysNotices = sysNoticeMapper.getAll();
            }else {
                sysNotices = sysNoticeMapper.selectByStatus(sysNotice.getStatus());
            }
        }else if(sysNotice.getStatus() == null || sysNotice.getStatus().equals("")){
            if(sysNotice.getNoticeType() == null || sysNotice.getNoticeType().equals("")){
                sysNotices = sysNoticeMapper.getAll();
            }else {
                sysNotices = sysNoticeMapper.selectByType(sysNotice.getNoticeType());
            }
        }else {
            sysNotices = sysNoticeMapper.selectForm(sysNotice);
        }
        return sysNotices;
    }


}
