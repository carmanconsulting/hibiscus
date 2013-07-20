package com.carmanconsulting.hibiscus.event.spring;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;

public class SessionFactoryEventListenerBootstrap extends AbstractEventListenerBootstrap
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final SessionFactoryImpl sessionFactory;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public SessionFactoryEventListenerBootstrap(SessionFactoryImpl sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected EventListenerRegistry getEventListenerRegistry()
    {
        return sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
    }
}
