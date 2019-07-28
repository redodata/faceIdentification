package com.xunpu.web;

import com.xunpu.model.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该类主要是关于上传文件的类
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class UploadFileServlet extends ImageBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String img=writeImage(req,"faceImage");//将上传的图片加密并转换为字节流
        if(img==null){
            responseJSON(resp,Response.ofFailed("","上传文件失败"));
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
