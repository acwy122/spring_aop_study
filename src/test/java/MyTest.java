import com.mashibing.service.MyCalculator;
import org.junit.Test;

public class MyTest {

    @Test
    public void test(){
        MyCalculator myCalculator = new MyCalculator();
        System.out.println(myCalculator.add(1,2));
    }
}