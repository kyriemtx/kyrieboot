package com.mtx.kyrieboot.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 11:07
 * @Description:
 */

@Controller
@RequestMapping("/kyrie/tool")
public class SwaggerController {

    @GetMapping("/swagger")
    public String index()
    {
        return redirect("/swagger-ui.html");
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
}
