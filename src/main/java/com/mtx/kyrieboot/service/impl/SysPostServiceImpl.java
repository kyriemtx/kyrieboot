package com.mtx.kyrieboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysPost;
import com.mtx.kyrieboot.mapper.SysPostMapper;
import com.mtx.kyrieboot.service.SysPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysPostServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 12:33
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPostServiceImpl implements SysPostService {

    @Autowired
    private SysPostMapper sysPostMapper;

    @Override
    public SysPost findByName(String postName) {
        log.info("【根据岗位名查询岗位信息接口】，请求参数 postName：{}",postName);
        SysPost sysPost = sysPostMapper.findByName(postName);
        log.info("【根据岗位名查询岗位信息接口】，返回参数：{}", JSON.toJSONString(sysPost));
        return sysPost;
    }

    @Override
    public IPage<SysPost> getAll(Page page) {
        return sysPostMapper.getAll(page);
    }

    @Override
    public List<SysPost> getAll() {
        return null;
    }

    @Override
    public SysPost selectById(String postId) {
        log.info("【根据岗位号查询岗位信息接口】，请求参数 postId：{}",postId);
        SysPost sysPost = sysPostMapper.selectByPostId(postId);
        log.info("【根据岗位号查询岗位信息接口】，返回参数：{}", JSON.toJSONString(sysPost));
        return sysPost;
    }

    @Override
    public int insertSysPost(SysPost sysPost) {
        log.info("【新增岗位信息接口】,请求参数：sysPost:{}",JSON.toJSONString(sysPost));
        int res = sysPostMapper.insert(sysPost);
        log.info("【新增岗位信息接口】,返回参数：res:{}",res);
        return res;
    }

    @Override
    public int updateSysPost(SysPost sysPost) {
        log.info("【修改岗位信息接口】,请求参数：sysPost:{}",JSON.toJSONString(sysPost));
        int res = sysPostMapper.updateById(sysPost);
        log.info("【修改岗位信息接口】,返回参数：res:{}",res);
        return res;
    }

    @Override
    public int deleteSysPost(String postId) {
        log.info("【删除岗位信息接口】,请求参数：postId:{}",postId);
        int res = sysPostMapper.deleteByPostId(postId);
        log.info("【删除岗位信息接口】,返回参数：res:{}",res);
        return res;
    }

    @Override
    public List<SysPost> selectForm(SysPost sysPost) {
        List<SysPost> sysPosts = new ArrayList<>();
        if(sysPost.getPostStatus().equals("")){
            if(sysPost.getPostName().equals("")){
                sysPosts = sysPostMapper.getAll();
            }else {
                SysPost post = sysPostMapper.findByName(sysPost.getPostName());
                sysPosts.add(post);
            }
        }else if(sysPost.getPostName().equals("")){
            if(sysPost.getPostStatus().equals("")){
                sysPosts = sysPostMapper.getAll();
            }else {
                sysPosts = sysPostMapper.selectByStatus(sysPost.getPostStatus());
            }
        }else {
            sysPosts = sysPostMapper.selectForm(sysPost);
        }
        return sysPosts;
    }
}
