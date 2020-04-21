package com.mtx.kyrieboot.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomInvalidSessionStrategy
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:24
 **/
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/invalid_session").forward(request,response);
    }
}
