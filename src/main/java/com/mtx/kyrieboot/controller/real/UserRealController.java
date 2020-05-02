package com.mtx.kyrieboot.controller.real;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.kyrieboot.base.AjaxResult;
import com.mtx.kyrieboot.base.CommonConstants;
import com.mtx.kyrieboot.entity.SysUser;
import com.mtx.kyrieboot.entity.SysUserRole;
import com.mtx.kyrieboot.service.SysRoleService;
import com.mtx.kyrieboot.service.SysUserRoleService;
import com.mtx.kyrieboot.service.SysUserService;
import com.mtx.kyrieboot.utils.UUIDUtils;
import com.mtx.kyrieboot.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRealController {


    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/getUserInfo")
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
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public AjaxResult updateRole(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser(userVO.getId(), userVO.getName(), null, userVO.getNickName(), userVO.getSex(), userVO.getMobile(),
                userVO.getEmail(), userVO.getBirthday(), userVO.getHobby(), userVO.getLiveAddress(), null, new Date());
        try {
            sysUserRoleService.deleteByUserId(userVO.getId());
            sysUserRoleService.insert(new SysUserRole(userVO.getId(),sysRoleService.getIdByName(userVO.getUserRole())));
            sysUserService.updateById(sysUser);
            jsonObject.put("code",200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }

    @PostMapping("/addUser")
    @ResponseBody
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
                    userVO.getMobile(), userVO.getEmail(), userVO.getBirthday(), userVO.getHobby(), userVO.getLiveAddress(), new Date(), null);
            if (sysUserService.insert(sysUser) > 0){
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
    public AjaxResult editPassword(String id){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(new BCryptPasswordEncoder().encode(CommonConstants.CZMM));
        try{
            if (sysUserService.updateById(sysUser) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return AjaxResult.success(jsonObject);
    }

    @GetMapping("/getAllRoleName")
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
        if (sysUserService.updateById(sysUser) > 0){
            jsonObject.put("code", 200);
            return AjaxResult.success(jsonObject);
        } else {
            return AjaxResult.fail("更新基本资料失败");
        }
    }
    
}
