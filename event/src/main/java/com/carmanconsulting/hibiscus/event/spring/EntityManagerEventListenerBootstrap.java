package com.carmanconsulting.hibiscus.event.spring;

import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;

public class EntityManagerEventListenerBootstrap extends AbstractEventListenerBootstrap
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final HibernateEntityManagerFactory entityManagerFactory;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EntityManagerEventListenerBootstrap(HibernateEntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected EventListenerRegistry getEventListenerRegistry()
    {
        SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) entityManagerFactory.getSessionFactory();
        return sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
    }
}
