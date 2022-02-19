package com.bloomberg.datawarehouse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @author monzer
 * To indicate that the attribute is rerquired
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequiredParam {
    /***
     * Message param is the message that appears on validation error
     * @return the message on error
     */
    String message() default "";
}
