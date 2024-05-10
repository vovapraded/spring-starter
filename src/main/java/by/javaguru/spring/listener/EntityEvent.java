package by.javaguru.spring.listener;

import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {
    public final AccessType accessType;
    public EntityEvent(Object source,AccessType accessType ) {
        super(source);
        this.accessType=accessType;
    }
}
