import java.lang.reflect.Proxy;

public class LoggerProxyBuilder {
    static LoggerInterface createProxy(){
        return (LoggerInterface) Proxy.newProxyInstance
                (
                        LoggerActions.class.getClassLoader(),
                        new Class<?>[]{LoggerInterface.class},
                        new LoggerInvocationHandler(new LoggerActions())
                );
    }
}
