package com.xunpu;

import com.baidu.aip.face.AipFace;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public class Sample {
    //����APPID/AK/SK
    public static final String APP_ID = "16879113";
    public static final String API_KEY = "twBwSMN5IkTKyM9vpVa0VS4P";
    public static final String SECRET_KEY = "NwqlbFKFyxIUMA5yziXLzl4EHFknYZlj";
    public static void main(String[] args) {
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
        options.put("max_face_num", "1");//�������ֶε����Ժ�������������map�С�
        // ��ʼ��һ��AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // ��ѡ�������������Ӳ���
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // ��ѡ�����ô����������ַ, http��socket��ѡһ�����߾�������
//        client.setHttpProxy("proxy_host", proxy_port);  // ����http����
//        client.setSocketProxy("proxy_host", proxy_port);  // ����socket����


        try {
            byte[] image1= IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\1.jpg"));
            // ���ýӿ�
            String image = Base64.getEncoder().encodeToString(image1);
            String imageType = "BASE64";

            // �������
            JSONObject res = client.detect(image, imageType,options);
            System.out.println(res.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

