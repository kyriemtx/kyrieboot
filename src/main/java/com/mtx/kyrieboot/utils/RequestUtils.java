package com.mtx.kyrieboot.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RequestUtils
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:52
 **/
public class RequestUtils {
    public static boolean isAjax(HttpServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
