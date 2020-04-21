package com.mtx.kyrieboot.entity.systeminfo;

import cn.hutool.core.util.NumberUtil;
import lombok.Setter;

/**
 * @ClassName HeapInfo
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:09
 **/
@Setter
public class HeapInfo {

    /**
     * 初始化
     */
    private double heapInit;

    /**
     * 堆最大
     */
    private double heapMax;

    /**
     * 堆已用
     */
    private double heapUsed;

    /**
     * 堆可用
     */
    private double heapCommitted;

    public double getHeapInit() {
        return NumberUtil.div(heapInit, (1024  * 1024), 2);
    }

    public double getHeapMax() {
        return NumberUtil.div(heapMax, (1024  * 1024), 2);
    }

    public double getHeapUsed() {
        return NumberUtil.div(heapUsed, (1024  * 1024), 2);
    }

    public double getHeapCommitted() {
        return NumberUtil.div(heapCommitted, (1024  * 1024), 2);
    }
}
