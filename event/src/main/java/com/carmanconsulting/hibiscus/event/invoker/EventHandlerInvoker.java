package com.carmanconsulting.hibiscus.event.invoker;

import org.apache.commons.proxy.Invoker;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventHandlerInvoker implements Invoker
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static Map<Class<?>, Object> primitiveValueMap = new HashMap<Class<?>, Object>();

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

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EventHandlerInvoker(Object targetObject, Method targetMethod, Method proxyMethod)
    {
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
            final Object returnValue = targetMethod.invoke(targetObject, objects);
            if (targetMethod.getReturnType().isInstance(returnValue))
            {
                return returnValue;
            }
        }
        if (method.getReturnType().isPrimitive())
        {
            return primitiveValueMap.get(method.getReturnType());
        }
        return null;
    }
}

