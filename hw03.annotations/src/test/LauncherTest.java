import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import main.classes.TestClass1;
import main.classes.TestClass2;
import main.classes.TestClass3;

import java.lang.reflect.InvocationTargetException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class LauncherTest {
    @Test
    public void TestLauncher() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Launcher launcher = context.getBean(Launcher.class);
        StatisticCounter counter = context.getBean(StatisticCounter.class);
        launcher.run(TestClass1.class);
        launcher.run(TestClass2.class);
        launcher.run(TestClass3.class);
        counter.print();
    }
}
