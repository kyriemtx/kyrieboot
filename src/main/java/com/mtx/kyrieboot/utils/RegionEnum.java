package com.mtx.kyrieboot.utils;

/**
 * @ClassName RegionEnum
 * @Description  区域级别枚举类
 * @Author tengxiao.ma
 * @Date 2020/4/30 13:48
 **/
public enum RegionEnum {

    LEVEL_0(0),
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    ;

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    RegionEnum(int level) {
        this.level = level;
    }
}
