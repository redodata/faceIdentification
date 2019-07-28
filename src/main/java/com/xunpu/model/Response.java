package com.xunpu.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * ��������ǰ����Ӧ��Ϣ��ģ�ͣ�����˽��յõ��Ľ��dataͨ��GSON��չʾ��ǰ̨���Լ���
 * @param <T>
 */
public class Response <T> {
    private static final Gson GSON=new GsonBuilder().serializeSpecialFloatingPointValues().create();

    private final static int OK=200;

    private final static int FAILED=600;

    private final int status;//��Ӧ״̬��

    private final String message;

    private final T data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);//����ǰjavaBean����ת��ΪJson�ַ���
    }

    private Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //����������Ӧ��
    public static  <T> Response<T> ofOk(T data){
        return new Response<>(OK,"",data);
    }
    //�����쳣��Ӧ��
    public static  <T> Response<T> ofFailed(T data,String message){
        return new Response<>(FAILED,message,data);
    }


}
