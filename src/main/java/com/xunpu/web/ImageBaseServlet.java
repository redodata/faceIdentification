package com.xunpu.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public abstract class ImageBaseServlet extends AbstractBaseServlet {
    private String storagePath;

    public String getStoragePath() {
        return this.storagePath;
    }

    @Override
    //主要用来获取图片存储路径并递归创建
    public void init() throws ServletException {
        super.init();
        this.storagePath = System.getProperty("user.home") + File.separator + "face";
        File directory = new File(this.storagePath);
        if (!directory.exists()) {
            boolean isExist = directory.mkdirs();//如果文件不存在就递归创建。
            if (!isExist) {
                //如果创建失败就抛出一个运行时异常
                throw new RuntimeException("create face directory failed"+this.storagePath);
            }
        }
    }

    //将上传的文件加密并转换为字节流返回
    public String writeImage(HttpServletRequest request, String parameter) {
        try {
            Part part = request.getPart(parameter);//用于获取使用form-data格式传递的HTTP请求的请求体，用于上传文件。
            String filename = DigestUtils.md5Hex(part.getSubmittedFileName() + System.currentTimeMillis());//获取提交的文件当前时间，并对该字符串进行加密
            try (InputStream in = part.getInputStream();
                 OutputStream out = new FileOutputStream(new File(this.storagePath, filename))
            ) {
                IOUtils.write(IOUtils.toByteArray(in), out);
                return filename;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //读图片  通过参数获取图片名，并转为二进制字节流返回。
    public byte[] readImage(HttpServletRequest request,String parameter){
        String filename=request.getParameter(parameter);
        try (InputStream in=new FileInputStream(new File(this.storagePath,filename))){
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
