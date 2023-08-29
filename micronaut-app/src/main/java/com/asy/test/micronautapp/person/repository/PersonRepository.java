package com.asy.test.micronautapp.person.repository;

import com.asy.test.micronautapp.person.model.Person;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
