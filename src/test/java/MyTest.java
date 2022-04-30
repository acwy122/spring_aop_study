import com.mashibing.proxy.CalculatorProxv;
import com.mashibing.service.Calculator;
import com.mashibing.service.MyCalculator;
import com.mashibing.service.impl.MyCalculator2;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

public class MyTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test() throws NoSuchMethodException{
//        MyCalculator myCalculator = new MyCalculator();
//        System.out.println(myCalculator.add(1,2));

//        Calculator calcylator = CalculatorProxv.getCalcylator(new MyCalculator());
//        calcylator.add(1,1);
//        calcylator.sub(1,1);
//        calcylator.mul(1,1);
//        calcylator.div(1,1);
//        System.out.println(calcylator.getClass());
    }

    @Test
    public void text02() throws NoSuchMethodException {
        MyCalculator calculator = context.getBean(MyCalculator.class);
        calculator.sub(1,1);
        calculator.add(1,1);
        System.out.println("-------------------");
        calculator.show(1);
        System.out.println(calculator.getClass());
    }

    @Test
    public void test03() throws NoSuchMethodException {
        MyCalculator2 myCalculator2 = context.getBean("myCalculator2", MyCalculator2.class);
        myCalculator2.add(1,2);

    }

}
