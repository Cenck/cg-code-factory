package com.cengel.code.task;

import com.cengel.code.model.CodeWritePojo;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhz
 * @version V1.0
 * @create 2018-03-11 16:35
 **/
public class FileWriteTask implements Runnable {

    private final CodeWritePojo pojo;

    //传入组织好的参数写文件
    public FileWriteTask(final CodeWritePojo pojo){
        this.pojo = pojo;
    }

    @Override
    public void run() {
        synchronized (pojo){
            try {
                String path = pojo.getPkg().replace(".", "\\");
                path = pojo.getRootPath() + "\\" + path;
                File pathFile = new File(path);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                path += "\\" + pojo.getFileName();
                /***
                 System.out.println("===========================");
                 System.out.println("==：写入文件"+pojo.getFileName());
                 System.out.println("===========================");
                 /***/
                try {
                    OutputStream os = new FileOutputStream(new File(path));
                    IOUtils.write(pojo.getDatas(), os);
                    IOUtils.closeQuietly(os);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }finally {

            }
        }
    }




}