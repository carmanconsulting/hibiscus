package com.carmanconsulting.hibiscus.event.spring;

import com.carmanconsulting.hibiscus.event.util.EventRecorder;
import com.carmanconsulting.hibiscus.event.util.TestEntity;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class AbstractBootstrapTest extends AbstractJUnit4SpringContextTests
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EventRecorder eventRecorder;

//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract void deleteImpl(Object entity);

    protected abstract void saveImpl(Object entity);

    protected abstract void updateImpl(Object entity);

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Before
    public void clearEvents()
    {
        eventRecorder.clearEvents();
    }

    protected void delete(final Object entity)
    {
        new TransactionTemplate(transactionManager).execute( new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus)
            {
                deleteImpl(entity);
            }
        });
    }

    protected void save(final Object entity)
    {
        new TransactionTemplate(transactionManager).execute( new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus)
            {
                saveImpl(entity);
            }
        });
    }

    @Test
    public void testInsertEvents()
    {
        save(new TestEntity());
        eventRecorder.assertEventCount(2);
        eventRecorder.assertEventOfType(PreInsertEvent.class, 0);
        eventRecorder.assertEventOfType(PostInsertEvent.class, 1);
    }

    @Test
    public void testUpdateEvents()
    {
        final TestEntity entity = new TestEntity();
        save(entity);
        update(entity);
    }

    protected void update(final Object entity)
    {
        new TransactionTemplate(transactionManager).execute( new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus)
            {
                updateImpl(entity);
            }
        });
    }
}
