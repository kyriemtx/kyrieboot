package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.entity.SysMenu;
import com.mtx.kyrieboot.entity.SysMenuRole;
import com.mtx.kyrieboot.entity.SysRole;
import com.mtx.kyrieboot.service.SysMenuRoleService;
import com.mtx.kyrieboot.service.SysMenuService;
import com.mtx.kyrieboot.service.SysRoleService;
import com.mtx.kyrieboot.utils.UUIDUtils;
import com.mtx.kyrieboot.vo.MenuListVo;
import com.mtx.kyrieboot.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 11:33
 * @Description:
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRealController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;


    @GetMapping("/getRoleInfo")
    public AjaxResult getRoleInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysRole> sysRoleList = sysRoleService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysRoleList.getTotal());
        jsonObject.put("page",sysRoleList.getCurrent());
        jsonObject.put("page_size",sysRoleList.getSize());
        jsonObject.put("sysRoleList",sysRoleList.getRecords());
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/deleteRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult deleteRole(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(id);
            sysRoleService.deleteById(id);
            jsonObject.put("code",200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/updateRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(roleVO.getId());
            for (String menuId : roleVO.getIds()){
                SysMenuRole sysMenuRole = new SysMenuRole(menuId, roleVO.getId());
                sysMenuRoleService.addMenuRole(sysMenuRole);
            }
            SysRole sysRole = new SysRole();
            sysRole.setId(roleVO.getId());
            sysRole.setName(roleVO.getName());
            sysRole.setAuthority(roleVO.getAuthority());
            sysRoleService.updateById(sysRole);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return AjaxResult.success(jsonObject);
    }


    @ResponseBody
    @GetMapping("/selectForm")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public AjaxResult selectForm(SysRole sysRole){
        JSONObject jsonObject = new JSONObject();
        List<SysRole> sysRoles = new ArrayList<>();
        if(sysRole != null){
            if(sysRole.getName() == null || sysRole.getName().equals("")){
                sysRoles = sysRoleService.getAll();
            }else {
                SysRole role = sysRoleService.getByName(sysRole.getName());
                sysRoles.add(role);
            }
        }else {
            sysRoles = sysRoleService.getAll();
        }
        return AjaxResult.success(jsonObject);


    }

    @PostMapping("/addRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult addRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            SysRole role = sysRoleService.getByName(roleVO.getName());
            if (role == null){
                String id = UUIDUtils.getUUID();
                for (String menuId : roleVO.getIds()){
                    SysMenuRole sysMenuRole = new SysMenuRole(menuId, id);
                    sysMenuRoleService.addMenuRole(sysMenuRole);
                }
                SysRole sysRole = new SysRole(id, roleVO.getName(), roleVO.getAuthority(), new Date());
                sysRoleService.insert(sysRole);
                jsonObject.put("code",200);
            } else {
                // 501 角色已存在
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getData")
    public AjaxResult getData(){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        jsonObject.put("menuList",listVoList);
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getRoleMenu")
    public AjaxResult getRoleMenu(@RequestParam("roleId")String roleId){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        List<String> parentIds = sysMenuService.getRoleMenu(roleId);
        List<String> roleMenuIds = sysMenuRoleService.getAllMenuId(roleId, parentIds);
        jsonObject.put("ids", roleMenuIds);
        jsonObject.put("parentIds", parentIds);
        jsonObject.put("menuList",listVoList);
        return AjaxResult.success(jsonObject);
    }

    private List<MenuListVo> getMenu(){
        List<MenuListVo> listVoList = new LinkedList<>();
        List<SysMenu> firstMenuList = sysMenuService.getFirstMenu();
        //组装数据
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
        return listVoList;
    }

}

