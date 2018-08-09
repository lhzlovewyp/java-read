package com.sea.reflect.dto;

/**
 *
 * 基于字段级别的注解的反射基础类.
 *
 * Created by lvhaizhen on 2018/8/8.
 */
public class ReflectAnnotationDTO {

    /**字段名称.*/
    private String parameterName;

    /**注解字段名称.*/
    private String annotationName;

    /**注解字段值.*/
    private Object annotationValue;


    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public Object getAnnotationValue() {
        return annotationValue;
    }

    public void setAnnotationValue(Object annotationValue) {
        this.annotationValue = annotationValue;
    }
}
