package com.asy.test.microprofile.person.repository;


import com.asy.test.microprofile.person.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager entityManager;

    public List<Person> findAll() {
        List<Person> resultList = entityManager.createNamedQuery("Person.findById", Person.class).getResultList();
        return resultList;
    }

    public Optional<Person> findById(String personId){
        //List allPeople = entityManager.createQuery("select p from Person p").getResultList();
        //return Optional.of(allPeople);
        Person person = entityManager.createNamedQuery("Person.findById", Person.class).getSingleResult();
        return Optional.of(person);
    }

    public void create(Person person) {
        entityManager.persist(person);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public void delete(Person person) {
        if (!entityManager.contains(person)) {
            person = entityManager.merge(person);
        }
        entityManager.remove(person);
    }

}
