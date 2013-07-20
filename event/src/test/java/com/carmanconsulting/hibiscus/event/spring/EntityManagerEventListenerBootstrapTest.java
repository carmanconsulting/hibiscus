package com.carmanconsulting.hibiscus.event.spring;

import com.carmanconsulting.hibiscus.event.util.EventHandler;
import com.carmanconsulting.hibiscus.event.util.TestEntity;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "entity-manager.xml")
public class EntityManagerEventListenerBootstrapTest extends AbstractJUnit4SpringContextTests
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EventHandler eventHandler;

    @Test
    public void doSomething()
    {
        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus)
            {
                final TestEntity entity = new TestEntity();
                entityManager.persist(entity);
            }
        });

        assertEquals(2, eventHandler.getEvents().size());
        assertTrue(eventHandler.getEvents().get(0) instanceof PreInsertEvent);
        assertTrue(eventHandler.getEvents().get(1) instanceof PostInsertEvent);
    }

}
