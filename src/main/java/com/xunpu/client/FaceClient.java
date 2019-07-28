package com.xunpu.client;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.xunpu.config.FaceInfo;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * 该类用于客户端获取人脸检测，同时对检测得到的信息进行解析。
 */
public class FaceClient {
    private final static String APP_ID = "16879113";
    private final static String API_KEY = "twBwSMN5IkTKyM9vpVa0VS4P";
    private final static String SECRET_KEY = "NwqlbFKFyxIUMA5yziXLzl4EHFknYZlj";
    private final static String image_Type = "BASE64";

    private static AipFace aipFace;

    public FaceClient() {
        aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        aipFace.setConnectionTimeoutInMillis(2000);//设置链接超时时间为0.2秒
        aipFace.setSocketTimeoutInMillis(60000);//设置响应超时时间为1分钟
    }



    //人脸检测
    public  FaceInfo detect(byte[] img)  {
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,race,quality,facetype");
        options.put("max_face_num", "1");
        JSONObject detect = aipFace.detect(byteToBase64(img), image_Type, options);
        //如果解析成功，就将JSONObject对象进行解析。设置默认空字符串的原因是：避免空指针异常。
        if (detect.optString("error_msg", "").equals("SUCCESS")) {
            JSONObject resObject = detect.optJSONObject("result");
                if (resObject.optInt("face_num") == 1) {
                    JSONObject faceObj = (JSONObject) resObject.optJSONArray("face_list").get(0);
                    FaceInfo faceInfo = parseAttribute(faceObj);
                    return faceInfo;
            }
        }
        return null;
        }
//将得到的json串进行解析（对每部分解析）
        private  FaceInfo parseAttribute (JSONObject faceobj){
            FaceInfo res = new FaceInfo();
            //表情
            String expression=faceobj.optJSONObject("expression").getString("type");
            switch (expression){
                case "smile":
                    res.setExpression("微笑");
                    break;
                case "laugh":
                    res.setExpression("大笑");
                    break;
                case "none":
                default:
                    res.setExpression("无");
                    break;
            }

            //face_shape脸形
            String faceShape=faceobj.optJSONObject("face_shape").getString("type");
            switch (faceShape){
                case "squre":
                    res.setFaceShape("国字脸");
                    break;
                case "triangle":
                    res.setFaceShape("瓜子脸");
                    break;
                case "oval":
                    res.setFaceShape("鹅蛋脸");
                    break;
                case "heart":
                    res.setFaceShape("心形脸");
                    break;
                case "round":
                    res.setFaceShape("圆形脸");
                    break;
                default:
                    res.setFaceShape("未知");
                    break;
            }
            //beauty颜值
            double beauty=faceobj.optDouble("beauty");
            res.setBeauty(beauty);

            //gender 性别
            String gender=faceobj.optJSONObject("gender").getString("type");
            switch (gender){
                case "female":
                    res.setGender("女");
                    break;
                case "male":
                    res.setGender("男");
                    break;
                    default:
                        res.setGender("未知");
                        break;
            }

            //race人种
            String race=faceobj.optJSONObject("race").getString("type");
            switch(race){
                case "yellow":
                    res.setRace("亚美人种");
                    break;
                case "white":
                    res.setRace("欧亚人种");
                    break;
                case "black":
                    res.setRace("赤道人种");
                    break;
                case "arabs":
                    res.setRace("大洋洲人种");
                    break;
                default:
                    res.setRace("未知");
                    break;
            }

            //glasses  是否带眼镜
            String glasses=faceobj.optJSONObject("glasses").getString("type");
            switch(glasses){
                case "common":
                    res.setGlasses("普通眼镜");
                    break;
                case "sun":
                    res.setGlasses("墨镜");
                    break;
                case "none":
                    res.setGlasses("无眼镜");
                    break;
                default:
                    res.setGlasses("未知");
                    break;
            }

            //年龄
            int age=faceobj.optInt("age");
            res.setAge(age);
            return res;
        }


        //人脸对比  直接调用aipFace.match(List对象)
        public Double match(byte[] image1,byte[] image2) {
            String imagestr1 =byteToBase64(image1);//将图片的byte[]形式转为Base64格式
            String imagestr2 = byteToBase64(image2);//将图片的byte[]形式转为Base64格式
            MatchRequest match1=new MatchRequest(imagestr1,image_Type);
            MatchRequest match2=new MatchRequest(imagestr2,image_Type);

            double res = 0.0;
            JSONObject jsonObj = aipFace.match(Arrays.asList(match1,match2));
            if (jsonObj.optString("error_msg","").equals("SUCCESS")) {
                //对比成功
                JSONObject resObj = jsonObj.optJSONObject("result");
                res = resObj.optDouble("score",0D);
                return res;
            } else {
                System.out.println("人脸对比失败");
                return 0D;
            }
        }

//将图片的byte[]转为Base64
    private String byteToBase64(byte[] image){
        if(image==null){
            image=new byte[]{};
        }
        return  Base64.getEncoder().encodeToString(image);
    }
    /**
     * 用于测试百度提供的两个函数
     */
//        public static void main (String[]args) throws IOException {
//            FaceClient faceClient = new FaceClient();
//            byte[] image1 = IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\1.jpg"));
//            byte[] image2=IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\2.jpg"));
//            double db=match(image1,image2);
//            System.out.println(db);
//        }

    }

