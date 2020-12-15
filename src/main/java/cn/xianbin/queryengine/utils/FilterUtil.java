package cn.xianbin.queryengine.utils;

import cn.xianbin.queryengine.request.function.FilterFunction;
import cn.xianbin.queryengine.request.function.FunctionCondition;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class FilterUtil {

    private static Map<String, Class<? extends FunctionCondition>> functionMap = new HashMap();

    static {
        Reflections reflections = new Reflections("cn.xianbin.queryengine.request.function");
        Set<Class<?>> FilterFunctionSet = reflections.getTypesAnnotatedWith(FilterFunction.class);

        FilterFunctionSet.forEach(clazz -> {
            FilterFunction filterFunction = clazz.getAnnotation(FilterFunction.class);
            if (filterFunction != null) {
                for (String funcName : filterFunction.name()) {
                    functionMap.put(funcName, (Class<? extends FunctionCondition>) clazz);
                }
            }
        });
    }

    public static FunctionCondition getFunctionCondition(String field, String function, List<Object> params) throws Exception {
        return (FunctionCondition) functionMap.get(function.toLowerCase())
                .getConstructor(String.class, String.class, List.class)
                .newInstance(field, function, params);
    }

    private FilterUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


}
