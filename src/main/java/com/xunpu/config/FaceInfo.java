package com.xunpu.config;

/**
 * 该类用来描述人脸的属性信息（7个）
 */
public class FaceInfo {
    /**
     * 年龄
     */
    private Integer age;

    @Override
    public String toString() {
        return "FaceInfo{" +
                "age=" + age +
                ", beauty=" + beauty +
                ", expression='" + expression + '\'' +
                ", faceShape='" + faceShape + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                ", glasses='" + glasses + '\'' +
                '}';
    }

    /**
     * 颜值
     */
    private Double beauty;

    /**
     * 表情
     */
    private String expression;

    /**
     * 脸型
     */
    private String faceShape;

    /**
     * 种族
     */
    private String race;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBeauty() {
        return beauty;
    }

    public void setBeauty(Double beauty) {
        this.beauty = beauty;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getFaceShape() {
        return faceShape;
    }

    public void setFaceShape(String faceShape) {
        this.faceShape = faceShape;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    /**
     * 性别
     */
    private String gender;

    /**
     * 眼镜
     */
    private String glasses;


}
