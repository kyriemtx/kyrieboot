package com.mtx.kyrieboot.entity;
import lombok.Builder;
import lombok.Data;

import	java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysMenu
 * @Description 菜单实体类
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:02
 **/
@Data
@Builder
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 571971263783693402L;

    private String id;

    private String parentId;

    private String menuName;

    private String menuCode;

    private String menuHref;

    private String menuIcon;

    private String menuLevel;

    private String menuWeight;

    private Boolean isShow;

    private Date createDate;

    private String createBy;

    public SysMenu(String id, String parentId, String menuName, String menuCode, String menuHref, String menuIcon,
                   String menuLevel, String menuWeight, Boolean isShow, Date createDate, String createBy){
        this.id = id;
        this.parentId = parentId;
        this.menuName = menuName;
        this.menuCode = menuCode;
        this.menuHref = menuHref;
        this.menuIcon = menuIcon;
        this.menuLevel = menuLevel;
        this.menuWeight = menuWeight;
        this.isShow = isShow;
        this.createDate = createDate;
        this.createBy = createBy;
    }
}
