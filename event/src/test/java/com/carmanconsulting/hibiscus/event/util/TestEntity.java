package com.carmanconsulting.hibiscus.event.util;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="TEST_ENTITY")
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

        return id.equals(that.id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
