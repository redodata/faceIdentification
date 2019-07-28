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
    //��Ҫ������ȡͼƬ�洢·�����ݹ鴴��
    public void init() throws ServletException {
        super.init();
        this.storagePath = System.getProperty("user.home") + File.separator + "face";
        File directory = new File(this.storagePath);
        if (!directory.exists()) {
            boolean isExist = directory.mkdirs();//����ļ������ھ͵ݹ鴴����
            if (!isExist) {
                //�������ʧ�ܾ��׳�һ������ʱ�쳣
                throw new RuntimeException("create face directory failed"+this.storagePath);
            }
        }
    }

    //���ϴ����ļ����ܲ�ת��Ϊ�ֽ�������
    public String writeImage(HttpServletRequest request, String parameter) {
        try {
            Part part = request.getPart(parameter);//���ڻ�ȡʹ��form-data��ʽ���ݵ�HTTP����������壬�����ϴ��ļ���
            String filename = DigestUtils.md5Hex(part.getSubmittedFileName() + System.currentTimeMillis());//��ȡ�ύ���ļ���ǰʱ�䣬���Ը��ַ������м���
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

    //��ͼƬ  ͨ��������ȡͼƬ������תΪ�������ֽ������ء�
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
