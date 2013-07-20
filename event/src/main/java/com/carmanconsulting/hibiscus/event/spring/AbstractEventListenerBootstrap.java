package com.carmanconsulting.hibiscus.event.spring;

import com.carmanconsulting.hibiscus.event.EventListenerUtils;
import com.carmanconsulting.hibiscus.event.annotation.EventListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.Map;

public abstract class AbstractEventListenerBootstrap implements ApplicationContextAware
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private ApplicationContext applicationContext;

//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract EventListenerRegistry getEventListenerRegistry();

//----------------------------------------------------------------------------------------------------------------------
// ApplicationContextAware Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @PostConstruct
    public void registerEventListeners()
    {
        final Map<String,Object> beans = applicationContext.getBeansWithAnnotation(EventListener.class);
        for (Map.Entry<String, Object> beanEntry : beans.entrySet())
        {
            logger.info("Searching bean {} for event handler methods.", beanEntry.getKey());
            EventListenerUtils.registerAnnotatedListeners(getEventListenerRegistry(), beanEntry.getValue());
        }
    }
}
