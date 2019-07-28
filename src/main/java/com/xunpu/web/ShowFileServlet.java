package com.xunpu.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/show"})
public class ShowFileServlet extends ImageBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] data=readImage(req,"id");
        if(data==null){
           resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }else{
            resp.getOutputStream().write(data);
            resp.getOutputStream().flush();
        }
    }
}
