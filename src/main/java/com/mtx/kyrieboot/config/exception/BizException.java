package com.mtx.kyrieboot.config.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName BizException
 * @Description  异常信息
 * @Author tengxiao.ma
 * @Date 2020/4/21 14:52
 **/
public class BizException extends AuthenticationException {
    public BizException(String msg) {
        super(msg);
    }
}
