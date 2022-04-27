package com.mashibing.proxy;

import com.mashibing.service.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorProxv {

    public static Calculator getCalcylator(final Calculator calculator){

        //获取被代理对象的类加载器
        ClassLoader loader = calculator.getClass().getClassLoader();
        //被代理对象的所有接口
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        //用来执行被代理类需要执行的方法
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName()+"开始执行，参数列表是"+ Arrays.asList(args));
                //开始调用被代理类的方法
                Object result = null;
                try{
                    result = method.invoke(calculator,args);
                    System.out.println(method.getName()+"开始执行，结果是"+ result);
                }catch(Exception e){
                    System.out.println(method.getName()+"方法抛出异常："+e.getMessage());
                    e.printStackTrace();
                }finally {
                    System.out.println(method.getName()+"方法执行结束。。。");
                }
                return result;
            }
        };
        //被代理对象的所有接口
        Object o = Proxy.newProxyInstance(loader, interfaces, handler);
        return (Calculator)o;
    }
}
