package com.mtx.kyrieboot.system.menu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.CommonConstants;
import com.mtx.kyrieboot.system.menu.entity.SysMenu;
import com.mtx.kyrieboot.system.menu.service.SysMenuService;
import com.mtx.kyrieboot.system.role.entity.SysRole;
import com.mtx.kyrieboot.system.menu.mapper.SysMenuMapper;
import com.mtx.kyrieboot.system.role.mapper.SysRoleMapper;
import com.mtx.kyrieboot.vo.MenuNameVO;
import com.mtx.kyrieboot.vo.MenuVo;
import com.mtx.kyrieboot.vo.SysMenuVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysMenuServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:57
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public List<MenuVo> getMenuByUser(String username) {
        //获取用户Role
        SysRole sysRole = sysRoleMapper.findByName(username);
        List<SysMenu> sysMenus = sysMenuMapper.findByRoleId(sysRole.getId());
        List<MenuVo> menuVoList = new ArrayList<>();

        // 取出一级菜单
        List<SysMenu> firstLevel = sysMenus.stream().filter(o -> o.getParentId() == null).collect(Collectors.toList());
        // 拼装二级菜单
        for (SysMenu sysMenu : firstLevel) {
            List<SysMenu> secodeMenuList = new LinkedList<>();
            for (SysMenu menu : sysMenus) {
                if (StringUtils.equals(menu.getParentId(),sysMenu.getId())){
                    secodeMenuList.add(menu);
                }
            }
            menuVoList.add(MenuVo.builder()
                    .name(sysMenu.getMenuName())
                    .code(sysMenu.getMenuCode())
                    .icon(CommonConstants.MENU_ICON_PREFIX + sysMenu.getMenuIcon())
                    .sysMenus(secodeMenuList)
                    .build());
        }
        return menuVoList;
    }

    @Override
    public List<SysMenu> findMenuListByUser(String username) {
        //获取用户Role
        SysRole sysRole = sysRoleMapper.findByName(username);
        return sysMenuMapper.findByRoleId(sysRole.getId());
    }

    @Override
    public IPage<SysMenu> findFirstMenu(Page page) {
        return sysMenuMapper.findFirstMenu(page);
    }

    @Override
    public List<SysMenu> findByParentId(String parentId) {
        return sysMenuMapper.findByParentId(parentId);
    }

    @Override
    public int updateMenu(SysMenuVO sysMenu) {
        return sysMenuMapper.updateMenu(sysMenu);
    }

    @Override
    public int addMenu(SysMenuVO sysMenu) {
        return sysMenuMapper.addMenu(sysMenu);
    }

    @Override
    public SysMenu getByName(String menuName, String menuCode, String menuHref) {
        return sysMenuMapper.getByName(menuName, menuCode, menuHref);
    }

    @Override
    public SysMenu getById(String id) {
        return sysMenuMapper.getById(id);
    }

    @Override
    public List<SysMenu> getFirstMenu() {
        return sysMenuMapper.getFirstMenu();
    }

    @Override
    public List<SysMenu> getSecondMenu() {
        return sysMenuMapper.getSecondMenu();
    }

    @Override
    public List<String> getRoleMenu(String roleId) {
        return sysMenuMapper.getRoleMenu(roleId);
    }

    @Override
    public List<String> getMenuLevel() {
        return sysMenuMapper.getMenuLevel();
    }

    @Override
    public List<MenuNameVO> getPreviousMenu(String menuLevel) {
        return sysMenuMapper.getPreviousMenu(menuLevel);
    }

    @Override
    public String getByMenuName(String menuNames) {
        return sysMenuMapper.getByMenuName(menuNames);
    }

    @Override
    public int deleteMenuById(String id) {
        return sysMenuMapper.deleteMenuById(id);
    }
}
