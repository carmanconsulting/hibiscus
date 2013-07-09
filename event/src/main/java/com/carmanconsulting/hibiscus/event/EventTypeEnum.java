package com.carmanconsulting.hibiscus.event;

import org.hibernate.event.spi.EventType;

public enum EventTypeEnum
{
    AUTO_FLUSH(EventType.AUTO_FLUSH),
    DELETE(EventType.DELETE),
    DIRTY_CHECK(EventType.DIRTY_CHECK),
    EVICT(EventType.EVICT),
    FLUSH(EventType.FLUSH),
    FLUSH_ENTITY(EventType.FLUSH_ENTITY),
    INIT_COLLECTION(EventType.INIT_COLLECTION),
    LOAD(EventType.LOAD),
    LOCK(EventType.LOCK),
    MERGE(EventType.MERGE),
    PERSIST(EventType.PERSIST),
    PERSIST_ONFLUSH(EventType.PERSIST_ONFLUSH),
    POST_COLLECTION_RECREATE(EventType.POST_COLLECTION_RECREATE),
    POST_COLLECTION_REMOVE(EventType.POST_COLLECTION_REMOVE),
    POST_COLLECTION_UPDATE(EventType.POST_COLLECTION_UPDATE),
    POST_COMMIT_DELETE(EventType.POST_COMMIT_DELETE),
    POST_COMMIT_INSERT(EventType.POST_COMMIT_INSERT),
    POST_COMMIT_UPDATE(EventType.POST_COMMIT_UPDATE),
    POST_DELETE(EventType.POST_DELETE),
    POST_INSERT(EventType.POST_INSERT),
    POST_LOAD(EventType.POST_LOAD),
    POST_UPDATE(EventType.POST_UPDATE),
    PRE_COLLECTION_RECREATE(EventType.PRE_COLLECTION_RECREATE),
    PRE_COLLECTION_REMOVE(EventType.PRE_COLLECTION_REMOVE),
    PRE_COLLECTION_UPDATE(EventType.PRE_COLLECTION_UPDATE),
    PRE_DELETE(EventType.PRE_DELETE),
    PRE_INSERT(EventType.PRE_INSERT),
    PRE_LOAD(EventType.PRE_LOAD),
    PRE_UPDATE(EventType.PRE_UPDATE),
    REFRESH(EventType.REFRESH),
    REPLICATE(EventType.REPLICATE),
    RESOLVE_NATURAL_ID(EventType.RESOLVE_NATURAL_ID),
    SAVE(EventType.SAVE),
    SAVE_UPDATE(EventType.SAVE_UPDATE),
    UPDATE(EventType.UPDATE);

//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final EventType eventType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private EventTypeEnum(EventType eventType)
    {
        this.eventType = eventType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public EventType getEventType()
    {
        return eventType;
    }
}
