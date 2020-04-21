package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtx.kyrieboot.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;

/**
 * @ClassName SysUserRoleMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:11
 **/
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {


    /**
     * 根据用户id删除用户和角色的联系
     * @param userId 用户id
     * @return 返回值
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    int deleteByUserId(String userId);
}

