package com.carmanconsulting.hibiscus.event;

import com.carmanconsulting.hibiscus.event.annotation.OnEvent;
import com.carmanconsulting.hibiscus.event.invoker.EventListenerInvoker;
import com.carmanconsulting.hibiscus.event.invoker.EventObjectAppendedInvoker;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.proxy.ProxyFactory;
import org.apache.commons.proxy.factory.javassist.JavassistProxyFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;

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
    private static <T> T createListener(EventTypeEnum eventType, Object targetObject, Method targetMethod)
    {
        for (Method proxyMethod : eventType.getEventType().baseListenerInterface().getDeclaredMethods())
        {
            if (isExactMatch(proxyMethod, targetMethod))
            {
                return (T) proxyFactory.createInvokerProxy(new EventListenerInvoker(eventType, targetObject, targetMethod, proxyMethod), new Class[]{eventType.getEventType().baseListenerInterface()});
            }
            else if(isEventObjectAppendedMatch(proxyMethod, targetMethod))
            {
                return (T) proxyFactory.createInvokerProxy(new EventObjectAppendedInvoker(eventType, targetObject, targetMethod, proxyMethod), new Class[] {eventType.getEventType().baseListenerInterface()});
            }
        }
        return null;
    }

    private static boolean isEventObjectAppendedMatch(Method proxyMethod, Method targetMethod)
    {
        final Class<?>[] targetParameterTypes = targetMethod.getParameterTypes();
        final Class<?>[] proxyParameterTypes = proxyMethod.getParameterTypes();
        final int proxyParameterCount = proxyParameterTypes.length;
        if(proxyParameterCount == targetParameterTypes.length - 1)
        {
            Class<?>[] prefix = new Class[proxyParameterCount];
            System.arraycopy(targetParameterTypes, 0, prefix, 0, proxyParameterCount);
            return ClassUtils.isAssignable(proxyParameterTypes, prefix);
        }
        return false;
    }

    private static boolean isExactMatch(Method proxyMethod, Method targetMethod)
    {
        return ClassUtils.isAssignable(proxyMethod.getParameterTypes(), targetMethod.getParameterTypes());
    }

    public static void registerAnnotatedListeners(EventListenerRegistry registry, Object targetObject)
    {
        for (Method targetMethod : targetObject.getClass().getDeclaredMethods())
        {
            OnEvent onEvent = targetMethod.getAnnotation(OnEvent.class);
            if (onEvent != null)
            {
                registerEventListener(registry, onEvent.value(), targetObject, targetMethod);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void registerEventListener(EventListenerRegistry registry, EventTypeEnum eventType, Object targetObject, Method targetMethod)
    {
        T listener = createListener(eventType, targetObject, targetMethod);
        if (listener != null)
        {
            registry.appendListeners(eventType.getEventType(), listener);
        }
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private EventListenerUtils()
    {
        
    }
}
