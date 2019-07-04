
import annotations.After;
import annotations.Before;
import annotations.Test;
import annotations.Tests;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.*;

public class Launcher {

    @Autowired
    Tests tests= new Tests();

    @SuppressWarnings("unchecked")
    public static void run(Class cls) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = cls.getDeclaredMethods();
        ArrayList<Method> publicMethods = getPublicMethods(methods);
        ArrayList<Method> beforeMethods = addMethodWithAnnotation(Before.class,publicMethods);
        ArrayList<Method> testMethods = addMethodWithAnnotation(Test.class,publicMethods);
        ArrayList<Method> afterMethods= addMethodWithAnnotation(After.class,publicMethods);

        for (Method testMethod : testMethods){
            Object  object = cls.getConstructor().newInstance();
            try {
                callMethods(beforeMethods,object);
                testMethod.invoke(object);
                callMethods(afterMethods,object);
            }catch (Exception exp){
                System.err.println("Exception in test" + testMethod.toString());
            }
        }
    }

    private static void callMethods(ArrayList<Method> methods,Object object) throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            method.invoke(object);
        }
    }
    private static ArrayList<Method> getPublicMethods(Method[] methods){
        ArrayList<Method> publicMethods = new ArrayList<Method>();
        for (Method method : methods) {
            if (method.getModifiers() != 1) {
                method.setAccessible(true); //Делаем как бы "public"
            }
            publicMethods.add(method);
        }
        return publicMethods;
    }
    private static ArrayList<Method> addMethodWithAnnotation(Class annotation, List<Method> methods) {
        ArrayList<Method> annotationsMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(annotation) != null) {
                annotationsMethods.add(method);
            }
        }
        return annotationsMethods;
    }

}
