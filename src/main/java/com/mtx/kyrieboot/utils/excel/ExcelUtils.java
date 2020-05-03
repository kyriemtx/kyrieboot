package com.mtx.kyrieboot.utils.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.mtx.kyrieboot.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelUtils
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/5/3 0:29
 **/
@Slf4j
public class ExcelUtils {


    /**
     * @param is    导入文件输入流
     * @param clazz Excel实体映射类
     * @return
     */
    public static <T extends BaseRowModel> List<T> readExcel(InputStream is, final Class<? extends BaseRowModel> clazz) {
        List<T> rowsList = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(is);
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = EasyExcelFactory.getReader(bis, listener);
            excelReader.read(new Sheet(1, 1, clazz));
            rowsList = listener.getRows();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsList;
    }

    /**
     * @param os    文件输出流
     * @param clazz Excel实体映射类
     * @param data  导出数据
     * @return
     */
    public static Boolean writeExcel(OutputStream os, Class clazz, List<? extends BaseRowModel> data,String sheetName) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(os);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, clazz);
            sheet1.setSheetName(sheetName);
            writer.write(data, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * ResponseEntity下载文件
     * @param fileName
     * @param byteOutPutStream
     */
    public static ResponseEntity<byte[]> downloadExcel(String fileName, ByteArrayOutputStream byteOutPutStream) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",
                    new String(fileName.getBytes("GBK"), "ISO8859-1"));
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(byteOutPutStream.toByteArray(), headers, HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 这是你最熟悉的老朋友Main方法，哈哈哈
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.导入Excel
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\work\\github\\boot_mp\\kyrieboot\\用户列表.xlsx");
            List<SysUser> userExcelModelList = ExcelUtils.readExcel(fis, SysUser.class);
            System.out.println("导入是否成功：-------------->"+"数据行数是："+userExcelModelList.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //2.导出Excel
       /* FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\work\\github\\boot_mp\\kyrieboot\\用户列表.xlsx");
            List<SysUser> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                SysUser userExcelModel = new SysUser();
                userExcelModel.setName("我是名字" + i);
                list.add(userExcelModel);
            }
            Boolean flag = ExcelUtils.writeExcel(fos, SysUser.class, list,"用户信息sheet");
            System.out.println("导出是否成功：" + flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/


    }
}
