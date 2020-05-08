package com.mtx.kyrieboot.kyriegen.controller.api;

import com.mtx.kyrieboot.kyriegen.service.KyrieGenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KyrieGenRealController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/8 11:50
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/gen")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KyrieGenRealController {

    @Autowired
    private KyrieGenService kyrieGenService;

   /* @GetMapping("/getAreaInfo")*/
    /*public AjaxResult getAreaInfo(@RequestParam("page") int page,
                                  @RequestParam("page_size") int pageSize){
        JSONObject jsonObject = new JSONObject();

    }*/
}
