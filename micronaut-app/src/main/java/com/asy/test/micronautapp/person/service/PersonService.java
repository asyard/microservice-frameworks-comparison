package com.asy.test.micronautapp.person.service;

import com.asy.test.micronautapp.person.core.WsException;
import com.asy.test.micronautapp.person.dto.PersonDto;
import com.asy.test.micronautapp.person.dto.PersonResponseDto;
import com.asy.test.micronautapp.person.mapper.PersonMapper;
import com.asy.test.micronautapp.person.model.Person;
import com.asy.test.micronautapp.person.repository.PersonRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private PersonMapper personMapper;

    public HttpResponse<List<PersonResponseDto>> getAll() {
        List<Person> personList = personRepository.findAll();
        return HttpResponse.ok().body(personMapper.toDtoList(personList));
    }

    public HttpResponse<?> findById(String personId) {
        Optional<Person> personOpt = personRepository.findById(personId);
        if (personOpt.isPresent()) {
            return HttpResponse.ok().body(personMapper.toDto(personOpt.get()));
        }
        return (HttpResponse.notFound().body("not_found"));
    }

    public HttpResponse<?> create(PersonDto personDto) {
        if (Objects.nonNull(personDto.id())) {
            return (HttpResponse.badRequest().body("id_should_be_null"));
        }
        Person person = new Person();
        person.setName(personDto.name());
        person.setEmail(personDto.email());

        personRepository.save(person);
        return HttpResponse.created((personMapper.toDto(person)));
    }

    @Transactional
    public HttpResponse<?> update(PersonDto personDto) {
        if (personDto.id() == null) {
            throw new WsException(HttpStatus.BAD_REQUEST.getCode(), "bad_request");
        }
        Optional<Person> personOpt = personRepository.findById(personDto.id());
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setName(personDto.name());
            person.setEmail(personDto.email());
            personRepository.update(person);
            return HttpResponse.ok().body(personMapper.toDto(person));
        }

        throw new WsException(HttpStatus.NOT_FOUND.getCode(), "person_not_found");
    }

}
