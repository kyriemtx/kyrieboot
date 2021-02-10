package com.mtx.kyrieboot.system.user.controller;

import com.mtx.kyrieboot.system.role.service.SysRoleService;
import com.mtx.kyrieboot.system.user.entity.SysUser;
import com.mtx.kyrieboot.system.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:05
 **/
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @GetMapping("/list")
    public String index(){
        return "module/user/user";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        SysUser sysUser = sysUserService.getById(id);
        String roleName = sysRoleService.getById(sysUser.getId());
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roleName", roleName);
        return "module/user/updateUser";
    }

    @GetMapping("/add")
    public String add(){
        return "module/user/addUser";
    }

    @GetMapping("/changePassword")
    public String changePassword(){
        return "module/user/changePassword";
    }

}
