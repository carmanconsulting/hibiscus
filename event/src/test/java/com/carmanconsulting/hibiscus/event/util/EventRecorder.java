package com.carmanconsulting.hibiscus.event.util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class EventRecorder
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private List<Object> events = new ArrayList<Object>(25);

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void clearEvents()
    {
        events.clear();
    }

    public void assertEventCount(int count)
    {
        assertEquals(count, events.size());
    }

    public void assertEventOfType(Class<?> eventType, int index)
    {
        assertTrue(eventType.isInstance(events.get(index)));
    }

    @SuppressWarnings("unchecked")
    public <T> T getEvent(int index)
    {
        return (T) events.get(index);
    }

    protected <T> void record(T event)
    {
        events.add(event);
    }
}
