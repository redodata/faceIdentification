package com.xunpu.config;

/**
 * ������������������������Ϣ��7����
 */
public class FaceInfo {
    /**
     * ����
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
     * ��ֵ
     */
    private Double beauty;

    /**
     * ����
     */
    private String expression;

    /**
     * ����
     */
    private String faceShape;

    /**
     * ����
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
     * �Ա�
     */
    private String gender;

    /**
     * �۾�
     */
    private String glasses;


}
