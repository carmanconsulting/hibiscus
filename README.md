# Event Listeners

Hibernate event listeners can be very powerful.  However, Spring doesn't include a nice way to register Spring-managed
beans as Hibernate event listeners.  The hibiscus-event library contains utility classes which make it easy to register
your Spring-managed beans as Hibernate event listeners.

## Writing an Event Listener

Writing an event listener is easy with Hibiscus!  All you have to do is annotate your bean class with the @EventListener
annotation (which allows it to be "scanned" by Spring) and annotate each event handler method with an @OnEvent
annotation.

```java
@EventListener
public class MyEventListener
{
  @OnEvent(EventTypeEnum.POST_COMMIT_INSERT)
  public void afterInsert(PostInsertEvent e)
  {
    // Do something
  }
}
```

This will allow your afterInsert() method to be called whenever a "post commit insert" event happens in Hibernate.  In
order to have your event listeners registered, you must first "bootstrap" them.

## Bootstrapping

In your Spring configuration, you will need to include a "bootstrap" bean:

```xml
<beans>
  <bean id="bootstrap" class="com.carmanconsulting.hibiscus.event.spring.EntityManagerEventListenerBootstrap">
    <constructor-arg ref="entityManagerFactory"/>
  </bean>
</beans>
```

This bean will search the Spring context for all @EventListener-annotated beans and register event listeners for each
@OnEvent-annotated methods contained in them.

## Type-Safe Events

Sometimes, you only want to handle events for certain entity types.  To achieve this, all you need to do is add a
parameter to your event handler method for the entity type you wish to support.  Hibiscus will automatically bind the
entity object to this parameter when your event handler is called.  Your event handler will not be called for events
with entities of a different type.

```java
@EventListener
public class MyEventListener
{
  @OnEvent(EventTypeEnum.POST_COMMIT_INSERT)
  public void onPostInsert(PostInsertEvent e, MyEntity myEntity)
  {
    // Do something
  }
}
```

Only events for entities of type MyEntity will be delivered to this event listener!