package com.carmanconsulting.hibiscus.event.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"session-factory.xml", "exact-match.xml"})
public class SessionFactoryEventListenerBootstrapTest extends AbstractBootstrapTest
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Autowired
    private SessionFactory sessionFactory;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected void deleteImpl(Object entity)
    {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    protected void saveImpl(Object entity)
    {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    protected void updateImpl(Object entity)
    {
        sessionFactory.getCurrentSession().update(entity);
    }
}
