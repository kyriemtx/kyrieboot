package com.mtx.kyrieboot.config.filter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName AccessAuthFilter
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 14:54
 **/
@Slf4j
public class AccessAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
