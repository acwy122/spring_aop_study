package com.mashibing.util;

import com.mashibing.service.MyCalculator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Order(200)
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
     *         使用通配符的时候，不是越简洁越好，更多的是要选择符合要求符合规则的方式，
     *         此时就要求在定义标识符的时候必须要遵循项目规范
     *
     *
     * 通知的正常执行顺序
     * 如果正常执行顺序：
     * before     after   afterReturning
     * 异常执行结束
     * begore     after   afterThrowing
     *      try{
     *          start
     *          code
     *          return
     *      }catch(){
     *          exception
     *      }finally{
     *          end
     *      }
     *
     *
     *      如果想要在方法中获取对应的参数或者方法名称等信息的时候，必须要使用joinPoint对象，并且此参数必须是第一个
     *      getSignature：获取方法签名
     *      getArgs：获取参数列表
     *      如果方法中有返回值，那么必须要在注解中添加returning="result"
     *      这个result必须要和参数列表中的名称保持一致
     *
     *      如果需要添加异常信息，那么在注解中要添加throwing="e"，这个e的名称必须跟参数列表中的名称保持一致
     *      如果想要添加其他参数,必须要添加args(参数列表)，ArgNames(参数列表)??
     *      @Before(value = "execution(public Integer com.mashibing.service.MyCalculator.*(Integer,Integer)) && args(joinPoint,i)",argNames = "joinPoint,i")
     *
     *      通知方法在定义的时候有没有什么特殊要求
     *      通知方法在定义的时候对于访问修饰符，返回值类型都没有明确的要求
     *      但是要注意，参数不能随意添加
     *
     *
     *      如果有多个匹配的表达式相同，能否做抽象？
     *          定义一个没有返回值的空方法，给改方法添加@PointCut注解，后续在使用的时候可以直接调用方法名称
     *          此处的方法只是起一个声明的左右，能够供内部的其他通知方法进行调用
     *
     *      环绕通知
     *          环绕通知再执行的时候是优先于普通通知的
     *          如果是正常结束，那么执行顺序是
     *          环绕前置通知    before   环绕后置通知   环绕返回通知   after  afterreturn
     *          如果是异常结束是
     *          环绕前置通知    before   环绕异常通知   环绕返回通知   after  afterreturn
     *          如果出现异常的时候在环绕通知中解决了，那么普通通知是接收不到的，如果想让普通通知接收到需要进行抛出throw throwable
     *          执行顺序改位：
     *          环绕前置通知    before   环绕异常通知   环绕返回通知   after  afterThrowing
     *
     *
     *      当应用程序中包含多个切面类的时候，具体的执行顺序是什么样的？
     *          按照切面类的名称首字母进行排序操作，按照字典序
     *          如果需要认为的规定顺序可以在切面类上添加@Order注解，同时添加具体的值
     *          值越小，越优先
     *
     */
    @Pointcut("execution(public Integer com.mashibing.service.MyCalculator.*(Integer,Integer))")
    public void myPointCut(){}

    @Pointcut("execution(* *(..))")
    public void myPointcut1(){}

    @Before(value = "myPointCut()")
    private void  start(JoinPoint joinPoint){
        //获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("log---"+signature.getName());
        //获取参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println("log---"+signature.getName()+"方法开始执行：参数是："+Arrays.asList(args));

        //MyCalculator.class.getMethod()
//        System.out.println("方法开始执行：参数是");
//        System.out.println(1);
    }

    @AfterReturning(value = "myPointcut1()", returning = "result")
    public static void stop(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        System.out.println("log---"+signature.getName()+"方法执行结束：参数是："+Arrays.asList(result));
    }


    @AfterThrowing(value = "myPointCut()",throwing = "e")
    public static void logException(JoinPoint joinPoint,Exception e){
        Signature signature = joinPoint.getSignature();
        System.out.println("log---"+signature.getName()+"方法执行异常："+e);

    }
    @After("myPointCut()")
    public static void logFinally(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("log---"+signature.getName()+"方法开始执行：参数是："+Arrays.asList(args));

    }

    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        Object[] args = pjp.getArgs();
        Object result = null;
        try {
            System.out.println("log-----"+"环绕通知start"+signature.getName()+"开始执行，参数为："+Arrays.asList(args));
            //通过反射的方式调用目标的方法，相当于执行method.invoke(),可以自己修改结果值
            result = pjp.proceed(args);
            result = 100;
            System.out.println("log-----"+"环绕通知stop"+signature.getName()+"方法执行结束");
        } catch (Throwable throwable) {
            System.out.println("log-----"+"环绕异常通知："+signature.getName()+"出现异常");
            throw throwable;

        }finally {
            System.out.println("log-----"+"环绕返回通知："+signature.getName()+"方法返回结果是："+result);
        }
        return result;
    }
}
