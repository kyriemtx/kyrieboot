package com.mtx.kyrieboot.vo;

import com.mtx.kyrieboot.entity.SysDept;
import lombok.Data;

import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/30 15:06
 * @Description:
 */
@Data
public class DeptTreeVo {

    private String deptId;
    private String deptName;
    private String order;
    private String phone;
    private String status;
    private String email;
    private List<SysDept> underDepts;


}
