# Event Listeners

The hibiscus-event library contains utility classes for implementing Hibernate event listeners more easily (especially
with Spring).  You can write a class like this:

```java
@EventListener
public class MyEventListener
{
  @OnEvent(EventTypeEnum.POST_COMMIT_INSERT)
  public void onPostInsert(PostInsertEvent e)
  {
    // Do something
  }
}
```

In your spring configuration, you must include a "bootstrap" bean:

```xml
<beans>
  <bean id="bootstrap" class="com.carmanconsulting.hibiscus.event.spring.EntityManagerEventListenerBootstrap">
    <constructor-arg ref="entityManagerFactory"/>
  </bean>
</beans>
```

Your @EventListener-annotated class will automatically included (assuming you use annotation config) and an event
listener will be registered which calls your onPostInsert() event handler method.

You can also use type-safe event handler methods:

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

