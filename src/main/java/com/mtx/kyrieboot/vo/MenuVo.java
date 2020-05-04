package com.mtx.kyrieboot.vo;

import com.mtx.kyrieboot.system.menu.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuVo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:56
 **/
@Data
@Builder
public class MenuVo {

    private String name;

    private String icon;

    private String code;

    private List<SysMenu> sysMenus;

}
