package com.asy.test.microprofile.person.core;


import com.asy.test.microprofile.person.dto.PersonDto;
import com.asy.test.microprofile.person.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.health.Startup;

@Startup
@ApplicationScoped
@Slf4j
public class DataLoader {

    @Inject
    PersonService personService;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object object) {
        log.info("Initializing dummy data");
        PersonDto dummyPerson1 = new PersonDto("person1", "person1@asy.mail");
        PersonDto dummyPerson2 = new PersonDto("person2", "person2@asy.mail");

        personService.create(dummyPerson1);
        personService.create(dummyPerson2);

    }
}
