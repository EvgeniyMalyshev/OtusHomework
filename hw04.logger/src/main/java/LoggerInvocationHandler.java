

import annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoggerInvocationHandler implements InvocationHandler {

    private Object originalObj;
    private Set<Method> listMethods = new HashSet<>();

    LoggerInvocationHandler(Object originalObj) {
        this.originalObj = originalObj;
        Class clazzObj = originalObj.getClass();
        Class<?>[] interfaces = clazzObj.getInterfaces();
        for (Method m : clazzObj.getDeclaredMethods()) {
            Arrays.stream(interfaces).anyMatch(ints ->
                    {
                        Method foundedMethodInt = null;
                        try {
                            foundedMethodInt = ints.getDeclaredMethod(m.getName(), m.getParameterTypes());
                        } catch (NoSuchMethodException e) {
                        }
                        if (foundedMethodInt != null) {
                            if (foundedMethodInt.isAnnotationPresent(Log.class) || m.isAnnotationPresent(Log.class)) {
                                listMethods.add(foundedMethodInt);
                                return true;
                            }
                        }
                        return false;
                    }
            );
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (listMethods.stream().anyMatch(m -> m.equals(method))) {
            System.out.print("executed method: " + method.getName() + ", param: ");
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < method.getParameterCount() - 1) System.out.print(", ");
            }
            System.out.println();
        }
        method.invoke(originalObj, args);
        return null;

    }
}
