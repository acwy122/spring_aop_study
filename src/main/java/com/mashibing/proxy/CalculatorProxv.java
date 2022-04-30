package com.mashibing.proxy;

import com.mashibing.service.Calculator;
import com.mashibing.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 必须要有接口，如果没有接口，不能使用，这种方式使用jdk提供的reflect包下的类
 * 但是在生产环境中我不能保证每个类都有实现的接口，所有第二种方式cglib
 *
 * cglib在实现的时候没有接口都无所谓
 *
 * 在spring里面使用了两种动态代理的方式，一种是jdk提供的（刚刚完成的）另外一种就是cglib
 *
 */
public class CalculatorProxv {

    public static Calculator getCalcylator(final Calculator calculator){

        //获取被代理对象的类加载器
        ClassLoader loader = calculator.getClass().getClassLoader();
        //被代理对象的所有接口
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        //用来执行被代理类需要执行的方法
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //开始调用被代理类的方法
                Object result = null;
                try{
//                    System.out.println(method.getName()+"开始执行，参数列表是"+ Arrays.asList(args));
//                    LogUtil.start(method,args);
                    result = method.invoke(calculator,args);
//                    System.out.println(method.getName()+"开始执行，结果是"+ result);
//                    LogUtil.stop(method,result);
                }catch(Exception e){
//                    LogUtil.logException(method,e);
                    e.printStackTrace();
                }finally {
//                    LogUtil.logFinally(method);
                }
                return result;
            }
        };
        //被代理对象的所有接口
        Object o = Proxy.newProxyInstance(loader, interfaces, handler);
        return (Calculator)o;
    }
}
