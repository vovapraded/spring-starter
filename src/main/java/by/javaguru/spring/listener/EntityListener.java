package by.javaguru.spring.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {
    @EventListener(condition = "#p0.accessType.name == 'DELETE'")
    public void acceptEntity(EntityEvent entityEvent){
        System.out.println(entityEvent);
    }
}
