package com.mtx.kyrieboot.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName MenuNameVO
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:55
 **/
@Data
@Builder
public class MenuNameVO {

    private String id;

    private String menuName;
}
