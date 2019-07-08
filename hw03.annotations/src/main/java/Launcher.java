import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.InvocationTargetException;
import static constants.Constants.*;


public class Launcher {
    @Autowired
    TestAnnotationsCalculate calculate;
    @Autowired
    StatisticCounter statisticCounter;


     @SuppressWarnings("unchecked")
         void run(Class cls) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

            var passed = 0;
            var failed = 0;

            var test = calculate.calculate(cls.getMethods());

            for (var method : test.get(TEST_METHODS)) {
                var isPassed = false;
                var testClassObj = cls.getDeclaredConstructor().newInstance();
                try {
                    for (var beforeMethod : test.get(BEFORE_METHODS)) {
                        beforeMethod.invoke(testClassObj);
                    }
                    method.invoke(testClassObj);
                    isPassed = true;
                    try {
                        for (var afterMethod : test.get(AFTER_METHODS)) {
                            afterMethod.invoke(testClassObj);
                        }
                    } catch (Exception ex) {
                        System.out.println("Catch failing test, current failed tests count: " + ++failed);
                        System.out.println(ex.getCause().getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println("Catch failing test, current failed tests count: " + ++failed);
                    System.out.println(ex.getCause().getMessage());
                }
                if (isPassed) {
                    passed++;
                    statisticCounter.count(PASSED, passed);

                } else {
                    statisticCounter.count(FAILED, failed);
                }
            }
            System.out.println("Testing results:\nTest passed = " + passed + "\nTest failed = " + failed);

        }
}
