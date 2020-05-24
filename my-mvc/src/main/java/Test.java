import com.yfs.mvc.MyDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");
        MyDao myDao = ctx.getBean("myDao", MyDao.class);
        HashMap<String, Object> result = myDao.getResult();
        System.out.println(result);

    }
}
