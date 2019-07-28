package com.xunpu.web;
import com.xunpu.client.FaceClient;
import com.xunpu.model.Response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractBaseServlet extends HttpServlet {
    protected FaceClient faceClient=new FaceClient();//����ǰ�˵����������������Աȵȹ��ܡ�

    protected void responseJSON(HttpServletResponse response, Response data) throws IOException {
//        ���ñ���ΪUTF-8
        response.setCharacterEncoding("UTF-8");
//        ���õ�ǰ��Ӧ������Ϊapplication/javascript���������ĵ���
        response.setContentType("application/javascript");
//        ��Ӧд��data��ˢ�¡�
       response.getWriter().write(data.toString());
        response.getWriter().flush();
    }
}

