package com.mtx.kyrieboot.common;

import com.mtx.kyrieboot.entity.SysDept;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/30 15:17
 * @Description: 组装树形结构
 */
public  class TreeCommon {

    /**
     * 判断是否有子节点
     * @param list
     * @param sysDept
     * @return
     */
    public static boolean hasChild(List<SysDept> list, SysDept sysDept) {
        return getChildList(list, sysDept).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     * @param list
     * @param sysDept
     * @return
     */
    public static List<SysDept> getChildList(List<SysDept> list, SysDept sysDept){
        List<SysDept> tlist = new ArrayList<SysDept>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()){
            SysDept sysDept1 = (SysDept) it.next();
            if(sysDept1.getParentId().equals(sysDept.getDeptId())){
                tlist.add(sysDept1);
            }
        }
        return tlist;
    }

    /**
     * 递归列表
     * @param list
     * @param sysDept
     */
    public static void recursionFn(List<SysDept> list, SysDept sysDept){
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, sysDept);
        sysDept.setChildren(childList);
        for(SysDept child : childList){
            if(hasChild(list,child)){
                //判断是否有子节点
                Iterator<SysDept> it = childList.iterator();
                while (it.hasNext()){
                    SysDept n = (SysDept) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }





}
