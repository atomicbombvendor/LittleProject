package com.company.InjectTest.annoationfactory.constant;


import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeedSource {
    FeedSourceType value();
}
