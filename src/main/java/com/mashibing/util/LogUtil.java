package com.mashibing.util;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtil {
    public static void start(Method method,Object ... objects){
        System.out.println(method.getName()+"方法开始执行：参数是"+ Arrays.asList(objects));
    }

    public static void stop(Object ... objects){
        System.out.println("方法开始执行：参数是"+ Arrays.asList(objects));
    }
}
