package com.xunpu.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 该类是向前端响应信息的模型（将后端接收得到的结果data通过GSON串展示到前台，以及）
 * @param <T>
 */
public class Response <T> {
    private static final Gson GSON=new GsonBuilder().serializeSpecialFloatingPointValues().create();

    private final static int OK=200;

    private final static int FAILED=600;

    private final int status;//响应状态码

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
        return GSON.toJson(this);//将当前javaBean对象转换为Json字符串
    }

    private Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //返回正常响应码
    public static  <T> Response<T> ofOk(T data){
        return new Response<>(OK,"",data);
    }
    //返回异常响应码
    public static  <T> Response<T> ofFailed(T data,String message){
        return new Response<>(FAILED,message,data);
    }


}
