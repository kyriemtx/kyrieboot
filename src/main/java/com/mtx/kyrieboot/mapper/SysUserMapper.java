package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName SysUserMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:07
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实例
     */
    @Select("SELECT * FROM sys_user WHERE name= #{name}")
    SysUser findByName(@Param("name") String name);

    /**
     * 查询所有用户
     * @param page 分页数据
     * @return 所有用户集合
     */
    @Select("SELECT * FROM sys_user")
    IPage<SysUser> getAll(Page page);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户集合
     */
    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id")String id);

    /**
     * 更新用户密码
     * @param password 密码
     * @param id id
     * @return 返回值
     */
    @Update("update sys_user set password = #{password} where id = #{id}")
    int updatePasswordById(@Param("password") String password,
                           @Param("id") String id);


    @Select("select * from sys_user")
    List<SysUser> selectAll();


    @Select("select * from sys_user where sex =#{sex}")
    List<SysUser> findBySex(@Param("sex") String sex);

    @Select("select * from sys_user where nick_name =#{nickName}")
    List<SysUser> findByNickName(@Param("nickName") String nickName);


}
