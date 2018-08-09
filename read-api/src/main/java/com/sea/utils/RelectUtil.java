package com.sea.utils;

import com.sea.reflect.dto.ReflectAnnotationDTO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * 反射工具类.
 *
 * Created by lvhaizhen on 2018/8/8.
 */
public class RelectUtil {

    public ReflectAnnotationDTO getMethodReflectAnnotationDTO(Method method,String annotationName){
        try {
            Annotation[][] annotations = method.getParameterAnnotations();
            for (Annotation[] an1 : annotations) {
                for (Annotation an2 : an1) {
                    Method m = an2.getClass().getDeclaredMethod(annotationName);

                    Object obj = (m.invoke(an2));
                    ReflectAnnotationDTO reflectAnnotationDTO = new ReflectAnnotationDTO();
                    reflectAnnotationDTO.setAnnotationName(annotationName);
                    reflectAnnotationDTO.setAnnotationValue(obj);
                    reflectAnnotationDTO.setParameterName("");
                    return reflectAnnotationDTO;
                }
            }

        } catch (Exception e){

        }
        return null;
    }
}
