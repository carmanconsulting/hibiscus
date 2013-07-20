package com.carmanconsulting.hibiscus.event.types;

import org.hibernate.event.spi.*;

public enum EventTypeEnum
{
    AUTO_FLUSH(EventType.AUTO_FLUSH),
    DELETE(EventType.DELETE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((DeleteEvent) event).getObject();
                }
            },
    DIRTY_CHECK(EventType.DIRTY_CHECK),
    EVICT(EventType.EVICT)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((EvictEvent)event).getObject();
                }
            },
    FLUSH(EventType.FLUSH),
    FLUSH_ENTITY(EventType.FLUSH_ENTITY)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((FlushEntityEvent)event).getEntity();
                }
            },
    INIT_COLLECTION(EventType.INIT_COLLECTION)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((InitializeCollectionEvent)event).getAffectedOwnerOrNull();
                }
            },
    LOAD(EventType.LOAD)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((LoadEvent)event).getInstanceToLoad();
                }
            },
    LOCK(EventType.LOCK)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((LockEvent)event).getObject();
                }
            },
    MERGE(EventType.MERGE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((MergeEvent)event).getEntity();
                }
            },
    PERSIST(EventType.PERSIST)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PersistEvent)event).getObject();
                }
            },
    PERSIST_ONFLUSH(EventType.PERSIST_ONFLUSH)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PersistEvent)event).getObject();
                }
            },
    POST_COLLECTION_RECREATE(EventType.POST_COLLECTION_RECREATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostCollectionRecreateEvent)event).getAffectedOwnerOrNull();
                }
            },
    POST_COLLECTION_REMOVE(EventType.POST_COLLECTION_REMOVE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostCollectionRemoveEvent)event).getAffectedOwnerOrNull();
                }
            },
    POST_COLLECTION_UPDATE(EventType.POST_COLLECTION_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostCollectionUpdateEvent)event).getAffectedOwnerOrNull();
                }
            },
    POST_COMMIT_DELETE(EventType.POST_COMMIT_DELETE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostDeleteEvent)event).getEntity();
                }
            },
    POST_COMMIT_INSERT(EventType.POST_COMMIT_INSERT)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostInsertEvent)event).getEntity();
                }
            },
    POST_COMMIT_UPDATE(EventType.POST_COMMIT_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostUpdateEvent)event).getEntity();
                }
            },
    POST_DELETE(EventType.POST_DELETE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostDeleteEvent)event).getEntity();
                }
            },
    POST_INSERT(EventType.POST_INSERT)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostInsertEvent)event).getEntity();
                }
            },
    POST_LOAD(EventType.POST_LOAD)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostLoadEvent)event).getEntity();
                }
            },
    POST_UPDATE(EventType.POST_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PostUpdateEvent)event).getEntity();
                }
            },
    PRE_COLLECTION_RECREATE(EventType.PRE_COLLECTION_RECREATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreCollectionRecreateEvent)event).getAffectedOwnerOrNull();
                }
            },
    PRE_COLLECTION_REMOVE(EventType.PRE_COLLECTION_REMOVE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreCollectionRemoveEvent)event).getAffectedOwnerOrNull();
                }
            },
    PRE_COLLECTION_UPDATE(EventType.PRE_COLLECTION_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreCollectionUpdateEvent)event).getAffectedOwnerOrNull();
                }
            },
    PRE_DELETE(EventType.PRE_DELETE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreDeleteEvent)event).getEntity();
                }
            },
    PRE_INSERT(EventType.PRE_INSERT)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreInsertEvent)event).getEntity();
                }
            },
    PRE_LOAD(EventType.PRE_LOAD)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreLoadEvent)event).getEntity();
                }
            },
    PRE_UPDATE(EventType.PRE_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((PreUpdateEvent)event).getEntity();
                }
            },
    REFRESH(EventType.REFRESH)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((RefreshEvent)event).getObject();
                }
            },
    REPLICATE(EventType.REPLICATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((ReplicateEvent)event).getObject();
                }
            },
    RESOLVE_NATURAL_ID(EventType.RESOLVE_NATURAL_ID),
    SAVE(EventType.SAVE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((SaveOrUpdateEvent)event).getEntity();
                }
            },
    SAVE_UPDATE(EventType.SAVE_UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((SaveOrUpdateEvent)event).getEntity();
                }
            },
    UPDATE(EventType.UPDATE)
            {
                @Override
                public Object getEntity(Object event)
                {
                    return ((SaveOrUpdateEvent)event).getEntity();
                }
            };

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

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public Object getEntity(Object event)
    {
        throw null;
    }
}
