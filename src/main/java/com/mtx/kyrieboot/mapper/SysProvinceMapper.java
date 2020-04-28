package com.mtx.kyrieboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.entity.SysProvince;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysProvinceMapper
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/28 11:45
 **/
@Mapper
public interface SysProvinceMapper  extends BaseMapper<SysProvince> {


    @Select("select * from sys_province where province_code =#{provinceCode}")
    SysProvince selectByCode(@Param("provinceCode") String provinceCode);


    @Select("select * from sys_province where province_name =#{provinceName}")
    SysProvince selectByName(@Param("provinceName") String provinceName);

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from sys_province")
    IPage<SysProvince> getAll(Page page);



    /**
     * 删除省份信息
     * @param Id
     * @return
     */
    @Delete("delete from sys_province where id =#{Id}")
    int deleteByPostId(@Param("Id") int Id);


    @Select("select * from sys_province")
    List<SysProvince> citySelect();

    @Select("select province_name from sys_province where province_code =#{provinceCode}")
    String selectProvinceNameByCode(@Param("provinceCode") String provinceCode);

}
