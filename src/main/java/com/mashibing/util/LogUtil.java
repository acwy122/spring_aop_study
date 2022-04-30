package com.mashibing.util;

import com.mashibing.service.MyCalculator;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogUtil {

    /**
     * 通知注解有以下集中类型
     *
     * @Before:前置通知，在方法执行之前完成
     * @After：后置通知，在方法执行完成之后通知
     * @afterBeturing：返回通知，在返回结果之后运行
     * @AfterThrowing：异常通知出现异常的时候使用
     * @Around：环绕通知
     *
     * 在方法的参数列表中不要随便添加参数值，会有异常信息
     *
     * 切入点表达式：
     * 最精确的匹配方式：
     *     public Integer com.mashibing.service.MyCalculator.add(Integer,Integer)
     *
     *     在实际的生产环境中，更多使用的是通配符的方式
     *     *
     *         1、可以用来匹配一个或者多个字符
     *         execution(public Integer com.mashibing.service.MyCalculator.*(Integer,Integer))
     *         2、匹配任意类型的参数，只能匹配一个
     *         execution(public Integer com.mashibing.service.MyCalculator.*(Integer,*))
     *         3、*在进行匹配的时候，只能匹配一层路径，不能匹配多层
     *         execution(public Integer com.mashibing.*.*.MyCalculator*.*(Integer,*))
     *         4、*不能够匹配访问修饰符，如果不饿能确定访问修饰符是什么，可以直接省略不写
     *         execution(Integer com.mashibing.service.MyCalculator.*(Integer,*))
     *         5、返回值可以使用*来代替
     *     ..
     *         1、可以匹配任意类型、多个参数
     *         execution(public Integer com.mashibing.service.MyCalculator.*(..))
     *         2、可以匹配多层路径
     *         execution(public Integer com.mashibing..MyCalculator*.*(..))
     *     最偷懒的方式
     *         execution( * *(..))
     *     如果表达式是以*开头，那么可以代替所有
     *         execution( * com..*(..))
     *         execution( * *.*(..))
     *
     *         在使用表达式的时候，还支持逻辑运算
     *         &&:两个或者多个条件必须同事满足
     *              execution(public Integer com.mashibing.service.MyCalculator.*(..)) && execution( * *.*(..))
     *         ||：多个条件满足一个即可
     *              execution(public Integer com.mashibing.service.MyCalculator.*(..)) || execution( * *(..))
     *         !：除了这种方法之外的都满足
     *              !execution( public Integer com.mashibing.service.MyCalculator.add(Integer,Integer))
     *         使用通配符的时候，不是越简洁越好，更多的是要选择符合要求符合规则的方式，此时就要求在定义标识符的时候必须要遵循项目规范
     *
     */
    @Before("!execution( public Integer com.mashibing.service.MyCalculator.add(Integer,Integer))")
    public static void start(){
        //MyCalculator.class.getMethod()
        System.out.println("方法开始执行：参数是");
    }

    @AfterReturning("execution(public Integer com.mashibing.service.MyCalculator.*(Integer,*))")
    public static void stop(){
        System.out.println("方法执行结果是：");
    }
    @AfterThrowing("execution(public Integer com.mashibing.service.MyCalculator.*(Integer,*))")
    public static void logException(){
        System.out.println("方法抛出异常：");
    }
    @After("execution(public Integer com.mashibing.service.MyCalculator.*(Integer,*))")
    public static void logFinally(){
        System.out.println("方法执行结束。。。");
    }
}
