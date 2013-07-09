package com.carmanconsulting.hibiscus.event.util;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class TestEntity
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Id
    private String id = UUID.randomUUID().toString();

//----------------------------------------------------------------------------------------------------------------------
// Canonical Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof TestEntity))
        {
            return false;
        }

        TestEntity that = (TestEntity) o;

        if (!id.equals(that.id))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
