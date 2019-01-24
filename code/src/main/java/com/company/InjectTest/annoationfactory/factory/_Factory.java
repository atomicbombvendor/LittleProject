package com.company.InjectTest.annoationfactory.factory;


import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class _Factory<S, A extends Annotation> {
    private final Set<Class<S>> sources;
    private final Class<A> annotationClass;

    protected _Factory(Class<?> parentClass, Class<A> annotationClass) {
        this.sources = this.lookup(parentClass, annotationClass);
        this.annotationClass = annotationClass;
    }

    /**
     * Get all children of type
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Set<Class<T>> lookup(Class<?> type) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder().filterInputsBy(new FilterBuilder().includePackage(type))
                .setUrls(ClasspathHelper.forPackage(type.getPackage().getName()));
        Reflections reflections = new Reflections(configurationBuilder);
        return (Set<Class<T>>) reflections.getSubTypesOf(type).stream().filter(c -> !Modifier.isAbstract(c.getModifiers()) && !c.isInterface()).map(c -> (T) c).collect(Collectors.toSet());
    }

    /**
     * For type, Get all children that contains the annotation
     *
     * @param type
     * @param annotation
     * @param <T>
     * @param <A>
     * @return
     */
    public <T, A extends Annotation> Set<Class<T>> lookup(Class<?> type, Class<A> annotation) {
        return (Set<Class<T>>) this.lookup(type).stream().filter(c -> c.getAnnotation(annotation) != null).map(c -> (T) c).collect(toSet());
    }

    protected S getElement(Function<A, Boolean> callbackFunc) throws Exception {
        for (Class<S> source : this.sources) {
            if (callbackFunc.apply(source.getAnnotation(this.annotationClass))) {
                try {
                    return (S) source.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new Exception(e);
                }
            }
        }
        return null;
    }
}
