package com.asy.test.microprofile;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AppLifeCycleBean {
    public void appStarted(@Observes @Initialized(ApplicationScoped.class) Object context) {
        log.info("Application is starting...");
    }

    public void appStopping(@Observes @Destroyed(ApplicationScoped.class) Object context) {
        log.info("Application is stopping...");
    }
}
