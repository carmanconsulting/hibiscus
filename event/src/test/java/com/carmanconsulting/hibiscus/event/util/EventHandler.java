package com.carmanconsulting.hibiscus.event.util;

import com.carmanconsulting.hibiscus.event.EventTypeEnum;
import com.carmanconsulting.hibiscus.event.annotation.OnEvent;
import org.hibernate.event.spi.PreInsertEvent;

public class EventHandler
{
    @OnEvent(EventTypeEnum.PRE_INSERT)
    public void onPreInsertEvent(PreInsertEvent e)
    {
        System.out.println("You're trying to insert!");
    }
}
