package com.xunpu;

import com.baidu.aip.face.AipFace;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16879113";
    public static final String API_KEY = "twBwSMN5IkTKyM9vpVa0VS4P";
    public static final String SECRET_KEY = "NwqlbFKFyxIUMA5yziXLzl4EHFknYZlj";
    public static void main(String[] args) {
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
        options.put("max_face_num", "1");//将脸部字段的属性和人脸数量放入map中。
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理


        try {
            byte[] image1= IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\1.jpg"));
            // 调用接口
            String image = Base64.getEncoder().encodeToString(image1);
            String imageType = "BASE64";

            // 人脸检测
            JSONObject res = client.detect(image, imageType,options);
            System.out.println(res.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

