package com.mtx.kyrieboot.vo;

import com.mtx.kyrieboot.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuListVo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:55
 **/
@Data
@Builder
public class MenuListVo {

    /**
     * 菜单主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单别名
     */
    private String menuCode;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单权重
     */
    private String menuWeight;

    /**
     * 菜单是否显示
     */
    private Boolean isShow;

    /**
     * 子菜单
     */
    private List<SysMenu> children;

}
