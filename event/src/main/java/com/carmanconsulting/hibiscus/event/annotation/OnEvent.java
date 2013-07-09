package com.carmanconsulting.hibiscus.event.annotation;

import com.carmanconsulting.hibiscus.event.EventTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnEvent
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    Class<?> entityType() default Object.class;
    EventTypeEnum value();
}
