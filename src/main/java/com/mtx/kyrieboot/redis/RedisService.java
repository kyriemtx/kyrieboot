package com.mtx.kyrieboot.redis;

/**
 * @ClassName RedisService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:00
 **/
public interface RedisService {

    /**
     * 查询验证码的值
     * @param key 键
     * @return /
     */
    String getCodeVal(String key);

    /**
     * 保存验证码
     * @param key 键
     * @param val 值
     */
    void saveCode(String key, Object val);

    /**
     * delete
     * @param key 键
     */
    void delete(String key);

}
