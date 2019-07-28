package com.xunpu.web;

import com.xunpu.model.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ������Ҫ�ǹ����ϴ��ļ�����
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class UploadFileServlet extends ImageBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String img=writeImage(req,"faceImage");//���ϴ���ͼƬ���ܲ�ת��Ϊ�ֽ���
        if(img==null){
            responseJSON(resp,Response.ofFailed("","�ϴ��ļ�ʧ��"));
        }else{
            String contextPath = req.getServletContext().getContextPath();
//            String url=(contextPath.isEmpty()==true?"show?id=":(contextPath+"/show?id="))+img;
            String url;
            if(!contextPath.isEmpty()){
                url=contextPath+"/show?id="+img;
            }else{
                url="/show?id="+img;
            }
            responseJSON(resp,Response.ofOk(url));
        }
    }
}
