package com.mtx.kyrieboot.base;

/**
 * @ClassName RespCodeEnum
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:41
 **/
public enum  RespCodeEnum {

    SUCCESS("200","成功"),
    INTERNAL_SERVER_ERROR("500","失败"),
    FIND_RES_NULL("100","未找到相关信息，请更换查询条件后重试"),
    ;
            ;

    private String code;
    private String msg;

    RespCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
