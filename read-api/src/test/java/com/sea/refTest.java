package com.sea;

import com.sea.controller.BackendLoginController;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 注解反射测试.
 * Created by lvhaizhen on 2018/8/8.
 */
public class refTest {

    public static void main(String args[]) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        Method method = BackendLoginController.class.getMethod("doLogin",String.class,String.class,String.class);
        Annotation[][] annotations = method.getParameterAnnotations();
        Parameter[] parameters = method.getParameters();

        for(Parameter parameter : parameters){
            System.out.println(parameter);
        }

//        for (Annotation[] an1 : annotations) {
//            for (Annotation an2 : an1) {
//                System.out.println(an2.annotationType().getSimpleName());
//                Method m = an2.getClass().getDeclaredMethod("required");
//                System.out.println(m.invoke(an2));
//            }
//        }

    }
}
