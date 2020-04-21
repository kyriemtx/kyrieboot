package com.mtx.kyrieboot.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AjaxResult
 * @Description 公共响应实体
 * @Author tengxiao.ma
 * @Date 2020/4/21 15:38
 **/
@Data
public class AjaxResult implements Serializable {

    private static final long serialVersionUID = -8042890715810347226L;

    public String respCode;

    public String respDesc;

    public Object respData;


   public AjaxResult(String respCode,String respDesc,Object respData){
       this.respCode = respCode;
       this.respDesc = respDesc;
       this.respData = respData;
   }

   public AjaxResult(RespCodeEnum respCodeEnum){
       this.respCode = respCodeEnum.getCode();
       this.respDesc = respCodeEnum.getMsg();
   }


   public AjaxResult(){
       this.respCode = RespCodeEnum.SUCCESS.getCode();
       this.respDesc = RespCodeEnum.SUCCESS.getMsg();
   }

    /**
     * 返回成功信息
     * @param message
     * @return
     */
   public static AjaxResult success(String message){
       return new AjaxResult(RespCodeEnum.SUCCESS.getCode(),message,null);

   }

    /**
     * 返回失败信息
     * @param message
     * @return
     */
   public static AjaxResult fail(String message){
       return new AjaxResult(RespCodeEnum.INTERNAL_SERVER_ERROR.getCode(),message,null);
   }

    /**
     * 成功返回数据
     * @param respData
     * @return
     */
   public static AjaxResult success(Object respData){
       AjaxResult ajaxResult = new AjaxResult(RespCodeEnum.SUCCESS);
       ajaxResult.setRespData(respData);
       return ajaxResult;
   }


}
