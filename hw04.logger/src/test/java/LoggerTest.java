import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {
    @Test
    public void logTest(){
        LoggerInterface loggerInterface = LoggerProxyBuilder.createProxy();
        Assert.assertNotNull(loggerInterface);
        loggerInterface.calculation(7);

    }
}
