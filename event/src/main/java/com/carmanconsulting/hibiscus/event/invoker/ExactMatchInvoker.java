package com.carmanconsulting.hibiscus.event.invoker;

import com.carmanconsulting.hibiscus.event.EventTypeEnum;
import org.apache.commons.proxy.Invoker;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ExactMatchInvoker implements Invoker
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static Map<Class<?>, Object> primitiveValueMap = new HashMap<Class<?>, Object>();

    private final EventTypeEnum eventType;
    private final Object targetObject;
    private final Method targetMethod;
    private final Method proxyMethod;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    static
    {
        primitiveValueMap.put(Integer.TYPE, 0);
        primitiveValueMap.put(Long.TYPE, (long) 0);
        primitiveValueMap.put(Short.TYPE, (short) 0);
        primitiveValueMap.put(Byte.TYPE, (byte) 0);
        primitiveValueMap.put(Float.TYPE, 0.0f);
        primitiveValueMap.put(Double.TYPE, 0.0);
        primitiveValueMap.put(Character.TYPE, (char) 0);
        primitiveValueMap.put(Boolean.TYPE, Boolean.FALSE);
    }

    protected static Object defaultReturnValue(Method method)
    {
        final Class<?> returnType = method.getReturnType();
        if(returnType.isPrimitive())
        {
            return primitiveValueMap.get(returnType);
        }
        return null;
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ExactMatchInvoker(EventTypeEnum eventType, Object targetObject, Method targetMethod, Method proxyMethod)
    {
        this.eventType = eventType;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.proxyMethod = proxyMethod;
    }

//----------------------------------------------------------------------------------------------------------------------
// Invoker Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        if (method.equals(proxyMethod))
        {
            final Object returnValue = invokeTargetMethod(targetObject, targetMethod, objects);
            if (proxyMethod.getReturnType().isInstance(returnValue))
            {
                return returnValue;
            }
        }
        return defaultReturnValue(proxyMethod);
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    EventTypeEnum getEventType()
    {
        return eventType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Object invokeTargetMethod(Object targetObject, Method targetMethod, Object[] parameters) throws Throwable
    {
        return targetMethod.invoke(targetObject, parameters);
    }
}
