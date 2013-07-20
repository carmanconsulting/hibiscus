package com.carmanconsulting.hibiscus.event.util;

import com.carmanconsulting.hibiscus.event.types.EventTypeEnum;
import com.carmanconsulting.hibiscus.event.annotation.EventListener;
import com.carmanconsulting.hibiscus.event.annotation.OnEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PreInsertEvent;

import java.util.LinkedList;
import java.util.List;

@EventListener
public class EventHandler
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private List<Object> events = new LinkedList<Object>();

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public List<Object> getEvents()
    {
        return events;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @OnEvent(EventTypeEnum.POST_COMMIT_INSERT)
    public void onPostInsert(PostInsertEvent e, TestEntity testEntity)
    {
        events.add(e);
    }

    @OnEvent(EventTypeEnum.PRE_INSERT)
    public void onPreInsertEvent(PreInsertEvent e, TestEntity testEntity)
    {
        events.add(e);
    }
}
