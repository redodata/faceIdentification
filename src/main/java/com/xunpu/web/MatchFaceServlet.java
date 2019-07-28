package com.xunpu.web;

import com.xunpu.model.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于从前端获取两张图片，然后在后端调用match()获取相似度值
 */
@WebServlet(urlPatterns = {"/match"})
public class MatchFaceServlet extends ImageBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] leftImg=readImage(req,"left");
        byte[] rightImg=readImage(req,"right");

        Double s=this.faceClient.match(leftImg,rightImg);
        responseJSON(resp,Response.ofOk(s.intValue()));
    }
}
