package com.xunpu.web;

import com.xunpu.config.FaceInfo;
import com.xunpu.model.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
@WebServlet(urlPatterns = {"/detect"})
public class DetectFaceServlet extends ImageBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       byte[] image=this.readImage(req,"faceImage");
       if(image==null){
           this.responseJSON(resp,Response.ofFailed("","»À¡≥ºÏ≤‚ ß∞‹"));
       }else{
           this.responseJSON(resp,Response.ofOk(this.faceClient.detect(image)));
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageBase64=req.getParameter("faceImage");
        byte[] data=Base64.getDecoder().decode(imageBase64);
        FaceInfo faceInfo=this.faceClient.detect(data);
        if(faceInfo!=null){
            this.responseJSON(resp,Response.ofOk(faceInfo));
        }else{
            this.responseJSON(resp,Response.ofFailed("","»À¡≥∑÷Œˆ ß∞‹"));
        }
    }
}
