package com.asy.test.quarkus.person.repository;

import com.asy.test.quarkus.person.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Optional<Person> findById(String personId){
        return find("id", personId).firstResultOptional();
    }
}
