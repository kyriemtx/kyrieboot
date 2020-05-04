package com.mtx.kyrieboot.monitor.server.entity;

import lombok.Data;

/**
 * @ClassName SysInfo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:11
 **/
@Data
public class SysInfo {

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}
