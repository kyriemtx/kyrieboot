package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysPost;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.AfterDomainEventPublication;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @ClassName SysPostMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/22 11:37
 **/
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {


    @Select("select p.post_id,p.post_name,p.post_code,p.post_sort,p.post_status,p.create_time,p.update_time," +
            "p.remark from sys_post p where post_name = #{postName}")
    SysPost findByName(@Param("postName") String postName);


    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from sys_post")
    IPage<SysPost> getAll(Page page);

    @Select("select * from sys_post")
    List<SysPost> getAll();

    @Select("select p.post_id,p.post_name,p.post_code,p.post_sort,p.post_status,p.create_time,p.update_time,p.remark from sys_post p where post_id =#{postId}")
    SysPost selectByPostId(@Param("postId") String postId);

    /**
     * 删除岗位信息
     * @param postId
     * @return
     */
    @Delete("delete from sys_post where post_id =#{postId}")
    int deleteByPostId(@Param("postId") String postId);


    @Select("select * from sys_post where post_status = #{postStatus}")
    List<SysPost> selectByStatus(@Param("postStatus")String postStatus);


    @Select("select * from sys_post where post_status =#{postStatus} and post_name like =#{postName}")
    List<SysPost> selectForm(SysPost sysPost);

}
