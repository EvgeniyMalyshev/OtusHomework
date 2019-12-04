import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static constants.Constants.AFTER_METHODS;
import static constants.Constants.BEFORE_METHODS;
import static constants.Constants.TEST_METHODS;



public class TestAnnotationsCalculate {
        Map<String, Set<Method>> calculate(Method[] methods) {
        Set<Method> beforeMethods = new HashSet<>();
        Set<Method> afterMethods = new HashSet<>();
        Set<Method> testMethods = new HashSet<>();
        Arrays.stream(methods).forEach(
                method -> {
                    if (method.isAnnotationPresent(Test.class)) {
                        testMethods.add(method);
                    } else {
                        if (method.isAnnotationPresent(Before.class)) {
                            beforeMethods.add(method);
                        } else {
                            if (method.isAnnotationPresent(After.class)) {
                                afterMethods.add(method);
                            }
                        }
                    }
                }
        );
        Map<String, Set<Method>> resultMethodMap = new HashMap<>();
        resultMethodMap.put(TEST_METHODS, testMethods);
        resultMethodMap.put(BEFORE_METHODS, beforeMethods);
        resultMethodMap.put(AFTER_METHODS, afterMethods);
        return resultMethodMap;
    }
}
