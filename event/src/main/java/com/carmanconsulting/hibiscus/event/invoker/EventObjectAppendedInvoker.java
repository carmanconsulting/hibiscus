package com.carmanconsulting.hibiscus.event.invoker;

import com.carmanconsulting.hibiscus.event.EventTypeEnum;

import java.lang.reflect.Method;

public class EventObjectAppendedInvoker extends ExactMatchInvoker
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EventObjectAppendedInvoker(EventTypeEnum eventType, Object targetObject, Method targetMethod, Method proxyMethod)
    {
        super(eventType, targetObject, targetMethod, proxyMethod);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected Object invokeTargetMethod(Object targetObject, Method targetMethod, Object[] parameters) throws Throwable
    {
        Object entity = getEventType().getEntity(parameters[0]);
        final Class<?>[] targetParameterTypes = targetMethod.getParameterTypes();
        if (entity == null || targetParameterTypes[targetParameterTypes.length - 1].isInstance(entity))
        {
            Object[] appended = new Object[targetParameterTypes.length];
            System.arraycopy(parameters, 0, appended, 0, parameters.length);
            appended[targetParameterTypes.length - 1] = entity;
            return targetMethod.invoke(targetObject, appended);
        }
        return null;
    }
}

