package com.mtx.kyrieboot.common;

import com.mtx.kyrieboot.entity.SysDept;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/30 11:38
 * @Description:
 */
public class CommonTest {
    public static void main(String[] args) throws IOException {
        SysDept sysDept = new SysDept();
        sysDept.setDeptName("测试");
        sysDept.setStatus("1");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\temp\\upload\\test.txt"));
        objectOutputStream.writeObject(objectOutputStream);
        objectOutputStream.close();
        System.out.printf("写入文件成功");


    }
}
