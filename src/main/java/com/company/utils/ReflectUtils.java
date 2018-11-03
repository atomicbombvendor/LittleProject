package com.company.utils;

import java.lang.invoke.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by atomic on 8/22/2017.
 */
public class ReflectUtils {
    private final static Object lockObj = new Object();
    private final static MethodHandles.Lookup caller = MethodHandles.lookup();
    private final static Map<Class, Map<String, Function>> getters
            = new ConcurrentHashMap<>();

    public static <T, R> R getPropValue(T obj, String prop, Class<R> propType) {
        if (obj == null) return null;

        Function<Object, R> getter = getGetter(obj.getClass(), prop, propType);
        return getter.apply(obj);
    }

    public static <R> Function<Object, R> getPropGetter(Class clazz, String prop, Class<R> propType) {
        return getGetter(clazz, prop, propType);
    }

    private static <R> Function<Object, R> getGetter(Class clazz, String prop, Class<R> propType) {
        if (getters.containsKey(clazz)) {
            Map<String, Function> map = getters.get(clazz);
            if (map.containsKey(prop))
                return map.get(prop);
        }

        synchronized (lockObj) {

            Map<String, Function> map;
            if (!getters.containsKey(clazz))
                map = new ConcurrentHashMap<>();
            else
                map = getters.get(clazz);

            Function func;
            if (!map.containsKey(prop)) {

                MethodHandle getterMH = null;
                try {
                    getterMH = caller.findVirtual(clazz, "get" + prop, MethodType.methodType(propType));
                    MethodType getter = getterMH.type();
                    CallSite site = LambdaMetafactory.metafactory(
                            caller,
                            "apply",
                            MethodType.methodType(Function.class),
                            getter.generic(),
                            getterMH,
                            getter);
                    MethodHandle factory = site.getTarget();
                    func = (Function) factory.invoke();
                    map.put(prop, func);
                } catch (Throwable throwable) {
                    throw new RuntimeException(throwable);
                }
            } else {
                func = map.get(prop);
            }

            if (!getters.containsKey(clazz))
                getters.put(clazz, map);

            return func;
        }
    }

    /**
     * Reflect Object property with Key-Value in map
     * @param obj
     * @param rows
     */
    public static void setPropValue(Object obj, Map<String, Object> rows) {

        if (obj == null || rows == null || rows.size() == 0) return;

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Map<String, Object> cRows = new HashMap<>();

        // convert column name to lower
        rows.forEach((k, v) -> {
            if (!cRows.containsKey(k.toLowerCase())) {
                cRows.put(k.toLowerCase(), v);
            }
        });

        for (Field field : fields) {

            String name = field.getName().toLowerCase();

            if (cRows.containsKey(name) &&
                    cRows.get(name) != null &&
                    !cRows.get(name).toString().equals("")) {

                field.setAccessible(true);

                String type = field.getType().toString();
                String value = cRows.get(name).toString();

                try {
                    Object cValue = null;

                    if (type.endsWith("String")) {
                        cValue = value;
                    } else if (type.endsWith("BigDecimal")) {
                        cValue = new BigDecimal(value);
                    } else if (type.endsWith("Integer") || type.endsWith("int")) {
                        if (name.endsWith("date")) {
                            cValue = value;
                        } else {
                            cValue = Integer.valueOf(value);
                        }
                    } else if (type.endsWith("Long") || type.endsWith("long")) {
                        if (name.endsWith("time")) {
                            cValue = DateTimeUtils.getDateTimeNumber(DateTimeUtils.getLocalDateTime(value, DateTimeUtils.yyyy_MM_dd_HH_mm_ss_SSS));
                        } else {
                            cValue = Long.valueOf(value);
                        }
                    } else if (type.endsWith("Boolean") || type.endsWith("boolean")) {
                        cValue = (value.toLowerCase().equals("true") || value.equals("1")) ? true : false;
                    } else {
                        continue;
                    }

                    field.set(obj, cValue);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Entity convert error for:" + clazz.getName() + "/" + field.getName() + "/" + value + "." + e.getMessage());
                }
            }
        }
    }
}
