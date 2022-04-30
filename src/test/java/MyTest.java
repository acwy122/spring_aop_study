import com.mashibing.proxy.CalculatorProxv;
import com.mashibing.service.Calculator;
import com.mashibing.service.MyCalculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

public class MyTest {

    @Test
    public void test() throws NoSuchMethodException{
//        MyCalculator myCalculator = new MyCalculator();
//        System.out.println(myCalculator.add(1,2));

        Calculator calcylator = CalculatorProxv.getCalcylator(new MyCalculator());
        calcylator.add(1,1);
        calcylator.sub(1,1);
        calcylator.mul(1,1);
        calcylator.div(1,1);
        System.out.println(calcylator.getClass());
    }

    @Test
    public void text02() throws NoSuchMethodException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Calculator calculator = context.getBean("myCalculator", Calculator.class);
        calculator.add(1,1);

    }

}
