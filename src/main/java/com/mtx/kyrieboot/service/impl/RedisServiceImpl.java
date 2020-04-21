package com.mtx.kyrieboot.service.impl;

import com.mtx.kyrieboot.service.RedisService;
import com.mtx.kyrieboot.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisServiceImpl
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:39
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisServiceImpl implements RedisService {

    private final RedisUtils redisUtils;

    @Value("${loginCode.expiration}")
    private Long expiration;

    @Override
    public String getCodeVal(String key) {
        try {
            return Objects.requireNonNull(redisUtils.get(key)).toString();
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void saveCode(String key, Object val) {
        redisUtils.set(key,val,expiration, TimeUnit.MINUTES);
    }

    @Override
    public void delete(String key) {
        redisUtils.remove(key);
    }
}
