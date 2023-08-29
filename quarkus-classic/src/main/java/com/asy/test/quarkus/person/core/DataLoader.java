package com.asy.test.quarkus.person.core;

import com.asy.test.quarkus.person.dto.PersonDto;
import com.asy.test.quarkus.person.service.PersonService;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@Startup
@ApplicationScoped
public class DataLoader {

    @Inject
    PersonService personService;

    public void onStart(@Observes StartupEvent startupEvent) {
        PersonDto dummyPerson1 = new PersonDto("person1", "person1@asy.mail");
        PersonDto dummyPerson2 = new PersonDto("person2", "person2@asy.mail");

        personService.create(dummyPerson1);
        personService.create(dummyPerson2);

    }
}
