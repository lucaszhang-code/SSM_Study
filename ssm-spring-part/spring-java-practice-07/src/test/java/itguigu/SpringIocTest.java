package itguigu;

import com.itguigu.config.JavaConfig;
import com.itguigu.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIocTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        StudentController studentController = applicationContext.getBean(StudentController.class);
        studentController.findAllStudents();
    }
}
