package com.carmanconsulting.hibiscus.event.util.exactmatch;

import com.carmanconsulting.hibiscus.event.annotation.EventListener;
import com.carmanconsulting.hibiscus.event.annotation.OnEvent;
import com.carmanconsulting.hibiscus.event.types.EventTypeEnum;
import com.carmanconsulting.hibiscus.event.util.EventRecorder;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PreInsertEvent;

import java.util.LinkedList;
import java.util.List;

@EventListener
public class ExactMatchEventRecorder extends EventRecorder
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @OnEvent(EventTypeEnum.POST_COMMIT_INSERT)
    public void onPostInsert(PostInsertEvent e)
    {
        record(e);
    }

    @OnEvent(EventTypeEnum.PRE_INSERT)
    public void onPreInsertEvent(PreInsertEvent e)
    {
        record(e);
    }
}
