package com.mtx.kyrieboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RedisVo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo implements Serializable {

    private String key;

    private String value;

}
