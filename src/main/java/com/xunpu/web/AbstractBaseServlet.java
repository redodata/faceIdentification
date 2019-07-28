package com.xunpu.web;
import com.xunpu.client.FaceClient;
import com.xunpu.model.Response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractBaseServlet extends HttpServlet {
    protected FaceClient faceClient=new FaceClient();//用于前端调用人脸检测和人脸对比等功能。

    protected void responseJSON(HttpServletResponse response, Response data) throws IOException {
//        设置编码为UTF-8
        response.setCharacterEncoding("UTF-8");
//        设置当前响应的类型为application/javascript。（根据文档）
        response.setContentType("application/javascript");
//        响应写回data并刷新。
       response.getWriter().write(data.toString());
        response.getWriter().flush();
    }
}

