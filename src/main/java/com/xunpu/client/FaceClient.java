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
 * �������ڿͻ��˻�ȡ������⣬ͬʱ�Լ��õ�����Ϣ���н�����
 */
public class FaceClient {
    private final static String APP_ID = "16879113";
    private final static String API_KEY = "twBwSMN5IkTKyM9vpVa0VS4P";
    private final static String SECRET_KEY = "NwqlbFKFyxIUMA5yziXLzl4EHFknYZlj";
    private final static String image_Type = "BASE64";

    private static AipFace aipFace;

    public FaceClient() {
        aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        aipFace.setConnectionTimeoutInMillis(2000);//�������ӳ�ʱʱ��Ϊ0.2��
        aipFace.setSocketTimeoutInMillis(60000);//������Ӧ��ʱʱ��Ϊ1����
    }



    //�������
    public  FaceInfo detect(byte[] img)  {
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,race,quality,facetype");
        options.put("max_face_num", "1");
        JSONObject detect = aipFace.detect(byteToBase64(img), image_Type, options);
        //��������ɹ����ͽ�JSONObject������н���������Ĭ�Ͽ��ַ�����ԭ���ǣ������ָ���쳣��
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
//���õ���json�����н�������ÿ���ֽ�����
        private  FaceInfo parseAttribute (JSONObject faceobj){
            FaceInfo res = new FaceInfo();
            //����
            String expression=faceobj.optJSONObject("expression").getString("type");
            switch (expression){
                case "smile":
                    res.setExpression("΢Ц");
                    break;
                case "laugh":
                    res.setExpression("��Ц");
                    break;
                case "none":
                default:
                    res.setExpression("��");
                    break;
            }

            //face_shape����
            String faceShape=faceobj.optJSONObject("face_shape").getString("type");
            switch (faceShape){
                case "squre":
                    res.setFaceShape("������");
                    break;
                case "triangle":
                    res.setFaceShape("������");
                    break;
                case "oval":
                    res.setFaceShape("�쵰��");
                    break;
                case "heart":
                    res.setFaceShape("������");
                    break;
                case "round":
                    res.setFaceShape("Բ����");
                    break;
                default:
                    res.setFaceShape("δ֪");
                    break;
            }
            //beauty��ֵ
            double beauty=faceobj.optDouble("beauty");
            res.setBeauty(beauty);

            //gender �Ա�
            String gender=faceobj.optJSONObject("gender").getString("type");
            switch (gender){
                case "female":
                    res.setGender("Ů");
                    break;
                case "male":
                    res.setGender("��");
                    break;
                    default:
                        res.setGender("δ֪");
                        break;
            }

            //race����
            String race=faceobj.optJSONObject("race").getString("type");
            switch(race){
                case "yellow":
                    res.setRace("��������");
                    break;
                case "white":
                    res.setRace("ŷ������");
                    break;
                case "black":
                    res.setRace("�������");
                    break;
                case "arabs":
                    res.setRace("����������");
                    break;
                default:
                    res.setRace("δ֪");
                    break;
            }

            //glasses  �Ƿ���۾�
            String glasses=faceobj.optJSONObject("glasses").getString("type");
            switch(glasses){
                case "common":
                    res.setGlasses("��ͨ�۾�");
                    break;
                case "sun":
                    res.setGlasses("ī��");
                    break;
                case "none":
                    res.setGlasses("���۾�");
                    break;
                default:
                    res.setGlasses("δ֪");
                    break;
            }

            //����
            int age=faceobj.optInt("age");
            res.setAge(age);
            return res;
        }


        //�����Ա�  ֱ�ӵ���aipFace.match(List����)
        public Double match(byte[] image1,byte[] image2) {
            String imagestr1 =byteToBase64(image1);//��ͼƬ��byte[]��ʽתΪBase64��ʽ
            String imagestr2 = byteToBase64(image2);//��ͼƬ��byte[]��ʽתΪBase64��ʽ
            MatchRequest match1=new MatchRequest(imagestr1,image_Type);
            MatchRequest match2=new MatchRequest(imagestr2,image_Type);

            double res = 0.0;
            JSONObject jsonObj = aipFace.match(Arrays.asList(match1,match2));
            if (jsonObj.optString("error_msg","").equals("SUCCESS")) {
                //�Աȳɹ�
                JSONObject resObj = jsonObj.optJSONObject("result");
                res = resObj.optDouble("score",0D);
                return res;
            } else {
                System.out.println("�����Ա�ʧ��");
                return 0D;
            }
        }

//��ͼƬ��byte[]תΪBase64
    private String byteToBase64(byte[] image){
        if(image==null){
            image=new byte[]{};
        }
        return  Base64.getEncoder().encodeToString(image);
    }
    /**
     * ���ڲ��԰ٶ��ṩ����������
     */
//        public static void main (String[]args) throws IOException {
//            FaceClient faceClient = new FaceClient();
//            byte[] image1 = IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\1.jpg"));
//            byte[] image2=IOUtils.toByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\2.jpg"));
//            double db=match(image1,image2);
//            System.out.println(db);
//        }

    }

