package com.mtx.kyrieboot.service;

import com.mtx.kyrieboot.entity.systeminfo.*;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import java.util.List;

/**
 * @ClassName SystemInfoService
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:14
 **/
public interface SystemInfoService {

    SystemHardwareInfo getSystemInfo();


    /**
     * 获取CPU相关信息
     */
    CpuInfo getCpuInfo(CentralProcessor processor);

    /**
     * 获取内存相关信息
     */
    MemInfo getMemInfo(GlobalMemory memory);

    /**
     * 获取JVM相关信息
     */
    JvmInfo getJvmInfo();

    /**
     * 获取堆内存信息
     */
    HeapInfo getHeapInfo();

    /**
     * 获取非堆内存信息
     */
    NoHeapInfo getNoHeapInfo();

    /**
     * 获取系统信息
     */
    SysInfo getSysInfo();

    /**
     * 获取磁盘信息
     */
    List<SysFileInfo> getSysFileInfo(OperatingSystem os);

}
