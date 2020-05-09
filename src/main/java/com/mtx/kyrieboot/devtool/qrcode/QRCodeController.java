package com.mtx.kyrieboot.devtool.qrcode;

import com.mtx.kyrieboot.utils.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName QRCodeController
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/9 9:58
 * @Version 1.0
 **/
@Controller
@RequestMapping("/qrcode")
@Slf4j
public class QRCodeController {

    @GetMapping("/index")
    public String index(){
        return "module/devutils/qrcode";
    }


    @GetMapping("/createQRCode")
    public void createQRCode(String codeContent, HttpServletResponse response){
        try{
            QRCodeUtils.createCodeToOutputStream(codeContent,response.getOutputStream());
        }catch (Exception e){
            log.info("生成二维码失败");
        }

    }



}
