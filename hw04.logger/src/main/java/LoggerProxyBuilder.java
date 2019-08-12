import java.lang.reflect.Proxy;

class LoggerProxyBuilder {
    static LoggerInterface createProxy() {
        return (LoggerInterface) Proxy.newProxyInstance
                (
                        LoggerActions.class.getClassLoader(),
                        new Class<?>[]{LoggerInterface.class},
                        new LoggerInvocationHandler(new LoggerActions())
                );
    }
}
