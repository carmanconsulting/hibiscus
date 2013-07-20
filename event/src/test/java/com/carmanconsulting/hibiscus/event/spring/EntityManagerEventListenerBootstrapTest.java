package com.carmanconsulting.hibiscus.event.spring;

import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(locations = {"entity-manager.xml", "entity-appended-listener.xml"})
public class EntityManagerEventListenerBootstrapTest extends AbstractBootstrapTest
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @PersistenceContext
    private EntityManager entityManager;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected void deleteImpl(Object entity)
    {
        entityManager.remove(entity);
    }

    @Override
    protected void saveImpl(Object entity)
    {
        entityManager.persist(entity);
    }

    @Override
    protected void updateImpl(Object entity)
    {
        entityManager.merge(entity);
    }
}
