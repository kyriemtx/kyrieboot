package com.mtx.kyrieboot.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 部门表(SysDept)实体类
 *
 * @author makejava
 * @since 2021-03-29 14:08:58
 */
public class SysDept implements Serializable {
    private static final long serialVersionUID = 637180646209639284L;
    /**
    * 部门id
    */
    private String deptId;
    /**
    * 父部门id
    */
    private String parentId;
    /**
    * 部门名称
    */
    private String deptName;
    /**
    * 显示顺序
    */
    private Integer orderNum;
    /**
    * 负责人
    */
    private String leader;
    /**
    * 联系电话
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 部门状态（0正常 1停用）
    */
    private String status;
    /**
    * 删除标志（0代表存在 2代表删除）
    */
    private String delFlag;
    /**
    * 创建者
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新者
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private Date updateTime;

    private List<SysDept> children;

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}