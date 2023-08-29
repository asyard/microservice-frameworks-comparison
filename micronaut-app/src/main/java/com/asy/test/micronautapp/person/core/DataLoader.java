package com.asy.test.micronautapp.person.core;

import com.asy.test.micronautapp.person.model.Person;
import com.asy.test.micronautapp.person.repository.PersonRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DataLoader implements ApplicationEventListener<ApplicationStartupEvent> {

    @Inject
    private PersonRepository personRepository;

    @Override
    public void onApplicationEvent(ApplicationStartupEvent event) {
        Person dummyPerson1 = new Person();
        dummyPerson1.setName("person1");
        dummyPerson1.setEmail("person1@asy.mail");

        Person dummyPerson2 = new Person();
        dummyPerson2.setName("person2");
        dummyPerson2.setEmail("person2@asy.mail");

        personRepository.save(dummyPerson1);
        personRepository.save(dummyPerson2);
    }
}
