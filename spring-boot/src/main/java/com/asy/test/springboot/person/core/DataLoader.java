package com.asy.test.springboot.person.core;

import com.asy.test.springboot.person.model.Person;
import com.asy.test.springboot.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private PersonRepository personRepository;

    @Autowired
    public DataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
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
