package com.carmanconsulting.hibiscus.event;

import com.carmanconsulting.hibiscus.event.annotation.OnEvent;
import com.carmanconsulting.hibiscus.event.invoker.EventHandlerInvoker;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.proxy.ProxyFactory;
import org.apache.commons.proxy.factory.javassist.JavassistProxyFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;

import java.lang.reflect.Method;

public class EventListenerUtils
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final ProxyFactory proxyFactory = new JavassistProxyFactory();

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private static <T> T createListener(EventType<T> eventType, Object targetObject, Method targetMethod)
    {
        for (Method proxyMethod : eventType.baseListenerInterface().getDeclaredMethods())
        {
            if (ClassUtils.isAssignable(targetMethod.getParameterTypes(), proxyMethod.getParameterTypes()))
            {
                return (T) proxyFactory.createInvokerProxy(new EventHandlerInvoker(targetObject, targetMethod, proxyMethod), new Class[]{eventType.baseListenerInterface()});
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> void registerEventListener(EventListenerRegistry registry, EventType<T> eventType, Object targetObject, Method targetMethod)
    {
        T listener = createListener(eventType, targetObject, targetMethod);
        if (listener != null)
        {
            registry.appendListeners(eventType, listener);
        }
    }

    public static void registerAnnotatedListeners(EventListenerRegistry registry, Object targetObject)
    {
        for (Method targetMethod : targetObject.getClass().getDeclaredMethods())
        {
            OnEvent onEvent = targetMethod.getAnnotation(OnEvent.class);
            if (onEvent != null)
            {
                registerEventListener(registry, onEvent.value().getEventType(), targetObject, targetMethod);
            }
        }
    }
}
