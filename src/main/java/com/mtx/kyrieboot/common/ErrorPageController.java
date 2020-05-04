package com.mtx.kyrieboot.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ErrorPageController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 19:02
 **/
@Controller
public class ErrorPageController implements ErrorController {


    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error(HttpServletResponse response){
        if (CommonConstants.INT_PAGE_ERROR == response.getStatus()){
            return CommonConstants.STRING_PAGE_ERROR;
        }
        return CommonConstants.STRING_PAGE_NOT_FOUND;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
