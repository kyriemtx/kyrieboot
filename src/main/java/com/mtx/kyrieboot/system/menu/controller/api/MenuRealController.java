package com.mtx.kyrieboot.system.menu.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.system.menu.entity.SysMenu;
import com.mtx.kyrieboot.system.menu.service.SysMenuService;
import com.mtx.kyrieboot.system.user.entity.SysUser;
import com.mtx.kyrieboot.system.user.service.SysUserService;
import com.mtx.kyrieboot.utils.SecurityUtils;
import com.mtx.kyrieboot.utils.UUIDUtils;
import com.mtx.kyrieboot.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MenuRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:11
 **/
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuRealController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/getMenulist")
    public AjaxResult getMenulist(){
        //获取当前用户登录用户
        Authentication userAuthentication = SecurityUtils.getCurrentUserAuthentication();
        String name = userAuthentication.getName();
        SysUser sysUser = sysUserService.findByName(name);
        List<MenuVo> menuVoList = sysMenuService.getMenuByUser(name);
        JSONObject data = new JSONObject(16);
        data.put("nickName",sysUser.getNickName());
        data.put("menuList",menuVoList);
        return AjaxResult.success(data);
    }

    @GetMapping("/getMenuInfo")
    public AjaxResult getMenuInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();

        /*page = (page -1) * pageSize;*/
        List<MenuListVo> listVoList = new LinkedList<>();
        IPage<SysMenu> firstMenu = sysMenuService.findFirstMenu(new Page(page, pageSize));
        //组装数据
        List<SysMenu> firstMenuList = firstMenu.getRecords();
        for (SysMenu sysMenu : firstMenuList) {
            List<SysMenu> secondMenu = sysMenuService.findByParentId(sysMenu.getId());
            listVoList.add(MenuListVo.builder().id(sysMenu.getId())
                    .children(secondMenu)
                    .isShow(sysMenu.getIsShow())
                    .menuCode(sysMenu.getMenuCode())
                    .menuHref(sysMenu.getMenuHref())
                    .menuIcon(sysMenu.getMenuIcon())
                    .menuLevel(sysMenu.getMenuLevel())
                    .menuName(sysMenu.getMenuName())
                    .menuWeight(sysMenu.getMenuWeight()).build());
        }
        jsonObject.put("total",firstMenu.getTotal());
        jsonObject.put("page",firstMenu.getCurrent());
        jsonObject.put("page_size",firstMenu.getSize());
        jsonObject.put("menuList",listVoList);
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/deleteMenu")
    public AjaxResult deleteMenu(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            if (sysMenuService.deleteMenuById(id) > 0){
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/updateMenu")
    @ResponseBody
    public AjaxResult updateMenu(@RequestBody SysMenuNameVO sysMenuNameVO){
        JSONObject jsonObject = new JSONObject();
        SysMenuVO sysMenu = new SysMenuVO(sysMenuNameVO.getId(),sysMenuService.getByMenuName(sysMenuNameVO.getMenuNames()),sysMenuNameVO.getMenuName(), sysMenuNameVO.getMenuCode(), sysMenuNameVO.getMenuHref(),sysMenuNameVO.getMenuIcon(),
                sysMenuNameVO.getMenuLevel(),sysMenuNameVO.getMenuWeight(),sysMenuNameVO.getIsShow(),null,null);
        try{
            if (sysMenuService.updateMenu(sysMenu) > 0){
                jsonObject.put("code",200);
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",500);
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/addMenu")
    @ResponseBody
    public AjaxResult addMenu(@RequestBody SysMenuNameVO sysMenuNameVO){
        JSONObject jsonObject = new JSONObject();
        SysMenu menu = sysMenuService.getByName(sysMenuNameVO.getMenuName(), sysMenuNameVO.getMenuCode(), sysMenuNameVO.getMenuHref());
        if (menu == null) {
            Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
            SysMenuVO sysMenuVO = new SysMenuVO(UUIDUtils.getUUID(),sysMenuService.getByMenuName(sysMenuNameVO.getMenuNames()),sysMenuNameVO.getMenuName(), sysMenuNameVO.getMenuCode(), sysMenuNameVO.getMenuHref(),sysMenuNameVO.getMenuIcon(),
                    sysMenuNameVO.getMenuLevel(),sysMenuNameVO.getMenuWeight(),sysMenuNameVO.getIsShow(),new Date(),(String)authentication.getPrincipal());
            try{
                if (sysMenuService.addMenu(sysMenuVO) > 0){
                    jsonObject.put("code",200);
                }
            }catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("code",500);
            }
        } else {
            jsonObject.put("code",501);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getMenuLevel")
    public AjaxResult getMenuLevel(){
        JSONObject jsonObject = new JSONObject();
        List<String> menuLevel = sysMenuService.getMenuLevel();
        jsonObject.put("menuLevel",menuLevel);
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getPreviousMenu")
    public AjaxResult getPreviousMenu(@RequestParam("menuLevel")String menuLevel){
        JSONObject jsonObject = new JSONObject();
        List<MenuNameVO> menuNames = sysMenuService.getPreviousMenu(String.valueOf((Integer.parseInt(menuLevel) - 1)));
        jsonObject.put("menuNames",menuNames);
        return AjaxResult.success(jsonObject);
    }

}
