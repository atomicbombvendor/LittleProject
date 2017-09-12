package com.company.InjectTest.SqlBuilder;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.util.ReflectionUtils.invokeMethod;

/**
 * Created by eli9 on 9/8/2017.
 */
@Component
public class SqlBuilder {

    //正则表达式 \\ 我要插入一个正则表达式的反斜线，所以其后的字符具有特殊的意义
    //\\{ 字符 {
    private static Pattern valuePattern = Pattern.compile("\\{\\{VALUES}}");

    interface TypeHandler {
        String handle(Object object);
    }

    private static Map<Class, TypeHandler> dispatch = new HashMap<Class, TypeHandler>(){
        {//匿名类调用方法，因为调用类的实例方法不能直接使用，需要在代码块内
            this.put(String.class, obj -> {
                StringJoiner stringJoiner = new StringJoiner("", "", "");
                return stringJoiner.add(obj.toString()).toString();
            });
            //这里 实现了handler
            this.put(Double.class, String::valueOf);
            this.put(Integer.class,String::valueOf);
            this.put(Long.class, String::valueOf);
            //另外的一种写法;匿名类和匿名接口的实现;将此种实现方法去掉不用的代码，就是上面的形式
            this.put(BigDecimal.class, new TypeHandler() {
                @Override
                public String handle(Object obj) {
                    return String.valueOf(obj);
                }
            });
        }
    };

    public String buildUpdate(List<CalcFinancialDataBalanceSheet> list){
        if(!list.isEmpty()){
            //得到list的对象class属性
            Class clazz = list.get(0).getClass();
            //传递了类的class。得到了一个对应的SqlTemplate
            SqlTemplate sqlTemplate = SqlTemplateCache.getSqlTemplate(clazz);
            DataBaseMapper dataBaseMapper = sqlTemplate.getDataBaseMapper();
            String sql = sqlTemplate.getUpdate();

            String value = list.stream()
                    .map(l -> dataBaseMapper.getMethodMap().values()
                            .stream().map(m -> {
                                Object obj = invokeMethod(m,l);
                                if(obj == null){
                                    return "NULL";
                                }else{
                                    return dispatch
                                            .get(obj.getClass())//得到TypeHandler
                                            .handle(obj);//
                                }
                            }).collect(Collectors.joining(",", "(", ")")))
                    .collect(Collectors.joining(","));

            Matcher m = valuePattern.matcher(sql);
            if(m.find()){
                //将目标字符串里与既有模式相匹配的子串全部替换为指定的字符串
                return m.replaceAll(value);
            }
        }
        return "";
    }

    private Object invokeMethod(Method method, CalcFinancialDataBalanceSheet obj){
        try {
            //invoke 执行对象的方法
            Object value = method.invoke(obj);
            return value;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
