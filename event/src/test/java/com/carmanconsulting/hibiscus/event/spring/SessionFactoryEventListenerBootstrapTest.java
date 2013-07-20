package com.carmanconsulting.hibiscus.event.spring;

import com.carmanconsulting.hibiscus.event.util.EventHandler;
import com.carmanconsulting.hibiscus.event.util.TestEntity;
import org.hibernate.SessionFactory;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "session-factory.xml")
public class SessionFactoryEventListenerBootstrapTest extends AbstractJUnit4SpringContextTests
{
    @Autowired
    private SessionFactory sessionFactory;

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
                sessionFactory.getCurrentSession().persist(entity);
            }
        });

        assertEquals(2, eventHandler.getEvents().size());
        assertTrue(eventHandler.getEvents().get(0) instanceof PreInsertEvent);
        assertTrue(eventHandler.getEvents().get(1) instanceof PostInsertEvent);
    }

}
