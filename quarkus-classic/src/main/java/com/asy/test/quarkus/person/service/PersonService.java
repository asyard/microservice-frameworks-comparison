package com.asy.test.quarkus.person.service;

import com.asy.test.quarkus.person.core.WsException;
import com.asy.test.quarkus.person.dto.PersonDto;
import com.asy.test.quarkus.person.dto.PersonResponseDto;
import com.asy.test.quarkus.person.mapper.PersonMapper;
import com.asy.test.quarkus.person.model.Person;
import com.asy.test.quarkus.person.repository.PersonRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @Inject
    PersonMapper personMapper;

    public List<PersonResponseDto> getAll() {
        PanacheQuery<Person> personList = personRepository.findAll();
        return personMapper.toDtoList(personList.list());
    }

    @Transactional
    public PersonResponseDto create(PersonDto personDto) {
        if (Objects.nonNull(personDto.id())) {
            throw new WsException(Response.Status.BAD_REQUEST.getStatusCode(), "Id should be null!");
        }
        Person person = new Person();
        person.setName(personDto.name());
        person.setEmail(personDto.email());

        personRepository.persistAndFlush(person); // used persistAndFlush instead of just persist so that created and updated timestamp values are persisted
        return personMapper.toDto(person);
    }

    @Transactional
    public PersonResponseDto update(PersonDto personDto) {
        Objects.requireNonNull(personDto.id(), "Id should not be null!");
        Optional<Person> personOpt = personRepository.findById(personDto.id());
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setName(personDto.name());
            person.setEmail(personDto.email());
            personRepository.persistAndFlush(person);
            return personMapper.toDto(person);
        }

        throw new WsException(Response.Status.NOT_FOUND.getStatusCode(), "not_found");
    }

    public PersonResponseDto findById(String personId) {
        // You can create the query directly. But this is not a good approach! Every time you call createQuery it will compile the query.
        // TypedQuery<Person> query = entityManager.createNamedQuery("Person.findById", Person.class);
        // query.setParameter("personId", personId);
        // Optional<Person> personOpt = query.getResultStream().findFirst();
        Optional<Person> personOpt = personRepository.findById(personId);
        if (personOpt.isPresent()) {
            return personMapper.toDto(personOpt.get());
        }
        throw new WsException(Response.Status.NOT_FOUND.getStatusCode(), "not_found");
    }

}
