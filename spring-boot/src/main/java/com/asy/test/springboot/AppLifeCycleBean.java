package com.asy.test.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppLifeCycleBean {

    @EventListener(ContextRefreshedEvent.class)
    public void onContextClosedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("The application is starting...");
    }

    // Closing event occurs chronologically after any other stop events
    // A stopped context can be restarted, but a closed one cannot be reopened.
    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent(ContextClosedEvent contextClosedEvent) {
        log.info("The application is stopping...");
    }

}
