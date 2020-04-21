package com.mtx.kyrieboot.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName SecurityUtils
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:53
 **/
public class SecurityUtils {

    /**
     * 获取当前用户
     */
    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
