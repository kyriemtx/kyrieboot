package com.mtx.kyrieboot.system.user.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.common.AjaxResult;
import com.mtx.kyrieboot.common.CommonConstants;
import com.mtx.kyrieboot.system.user.entity.SysUser;
import com.mtx.kyrieboot.system.role.entity.SysUserRole;
import com.mtx.kyrieboot.system.role.service.SysRoleService;
import com.mtx.kyrieboot.system.role.service.SysUserRoleService;
import com.mtx.kyrieboot.system.user.service.SysUserService;
import com.mtx.kyrieboot.utils.UUIDUtils;
import com.mtx.kyrieboot.utils.excel.ExcelUtils;
import com.mtx.kyrieboot.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:18
 **/

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRealController {


    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户列表",notes = "查询所有用户列表")
    public AjaxResult getUserInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        List<UserVO> userList = new ArrayList<>(16);
        IPage<SysUser> sysUserList = sysUserService.getAll(new Page(page, pageSize));
        if (sysUserList.getRecords() != null && sysUserList.getRecords().size() > 0){
            for (SysUser sysUser : sysUserList.getRecords()){
                //根据用户id查询角色名称
                String roleName = sysRoleService.getById(sysUser.getId());
                UserVO userVO = new UserVO(roleName);
                userVO.setId(sysUser.getId());
                userVO.setName(sysUser.getName());
                userVO.setNickName(sysUser.getNickName());
                userVO.setSex(sysUser.getSex());
                userVO.setMobile(sysUser.getMobile());
                userVO.setEmail(sysUser.getEmail());
                userList.add(userVO);
            }
        }
        jsonObject.put("total",sysUserList.getTotal());
        jsonObject.put("page",sysUserList.getCurrent());
        jsonObject.put("page_size",sysUserList.getSize());
        jsonObject.put("sysUserList",userList);
        return AjaxResult.success(jsonObject);
    }

    /**
     * 表单查询
     * @param sysUser
     * @return
     */
    @GetMapping("/selectForm")
    @ResponseBody
    @ApiOperation(value = "表单查询",notes = "表单条件查询")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult selectForm(SysUser sysUser){
        JSONObject jsonObject = new JSONObject();
        if(sysUser == null){
            IPage<SysUser> sysUserList = sysUserService.getAll(new Page(1, 10));
            jsonObject.put("sysUserList",sysUserList.getRecords());
        }else {
            List<SysUser> sysUsers = sysUserService.selectForm(sysUser);
            jsonObject.put("sysUserList",sysUsers);
        }
        return AjaxResult.success(jsonObject);
    }


    @GetMapping("/deleteUser")
    @ApiOperation(value = "删除用户信息",notes = "删除用户接口")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult deleteUser(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            sysUserRoleService.deleteByUserId(id);
            sysUserService.deleteById(id);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    @ApiOperation(value = "更新用户信息",notes = "更新用户接口")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateRole(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser(userVO.getId(), userVO.getName(), null, userVO.getNickName(), userVO.getSex(), userVO.getMobile(),
                userVO.getEmail(), userVO.getBirthday(), userVO.getHobby(), userVO.getLiveAddress());
        try {
            sysUserRoleService.deleteByUserId(userVO.getId());
            sysUserRoleService.insert(new SysUserRole(userVO.getId(),sysRoleService.getIdByName(userVO.getUserRole())));
            sysUserService.updateUser(sysUser);
            jsonObject.put("code",200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/addUser")
    @ResponseBody
    @ApiOperation(value = "新增用户信息",notes = "新增用户接口")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult addRole(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser user = sysUserService.findByName(userVO.getName());
        if (user == null){
            //用户id
            String userId = UUIDUtils.getUUID();
            //角色id
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysRoleService.getIdByName(userVO.getUserRole()));
            sysUserRole.setUserId(userId);
            sysUserRoleService.insert(sysUserRole);
            SysUser sysUser = new SysUser(userId, userVO.getName(), new BCryptPasswordEncoder().encode(userVO.getPassword()), userVO.getNickName(), userVO.getSex(),
                    userVO.getMobile(), userVO.getEmail(), userVO.getBirthday(), userVO.getHobby(), userVO.getLiveAddress());
            if (sysUserService.insertUser(sysUser) > 0){
                jsonObject.put("code", 200);
            } else {
                jsonObject.put("code", 500);
            }
        } else {
            // 501 用户已存在
            jsonObject.put("code", 501);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/editPassword")
    @ApiOperation(value = "更改用户密码",notes = "更新用户密码")
    public AjaxResult editPassword(String id){
        JSONObject jsonObject = new JSONObject();
        String newPassword = new BCryptPasswordEncoder().encode(CommonConstants.CZMM);
        try{
            if (sysUserService.resetPassword(newPassword,id) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getAllRoleName")
    @ApiOperation(value = "获取用户角色",notes = "获取用户角色")
    public AjaxResult getAllRoleName(){
        JSONObject jsonObject = new JSONObject();
        List<String> allRoleName = sysRoleService.getAllRoleName();
        jsonObject.put("allRoleName", allRoleName);
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/editUser")
    @ResponseBody
    public AjaxResult editUser(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        sysUser.setId(userVO.getId());
        sysUser.setName(userVO.getName());
        sysUser.setNickName(userVO.getNickName());
        sysUser.setEmail(userVO.getEmail());
        sysUser.setSex(userVO.getSex());
        sysUser.setMobile(userVO.getMobile());
        sysUser.setBirthday(userVO.getBirthday());
        sysUser.setHobby(userVO.getHobby());
        sysUser.setLiveAddress(userVO.getLiveAddress());
        sysUser.setUpdateTime(new Date());
        if (sysUserService.updateUser(sysUser) > 0){
            jsonObject.put("code", 200);
            return AjaxResult.success(jsonObject);
        } else {
            return AjaxResult.fail("更新基本资料失败");
        }
    }

    @ResponseBody
    @RequestMapping("/export")
    @ApiOperation(value = "导出用户信息",notes = "导出用户信息")
    public AjaxResult export(){
        JSONObject jsonObject = new JSONObject();
        FileOutputStream fos = null;
        String sheetName = "userList";
        try {
            fos = new FileOutputStream("D:\\work\\github\\boot_mp\\kyrieboot\\用户列表.xlsx");
            List<SysUser> users = sysUserService.selectForm(new SysUser());
            Boolean flag = ExcelUtils.writeExcel(fos, SysUser.class, users,sheetName);
            if(flag == true){
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code",500);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return AjaxResult.success(jsonObject);
    }



}
