package com.asy.test.microprofile.person.service;


import com.asy.test.microprofile.person.core.WsException;
import com.asy.test.microprofile.person.dto.PersonDto;
import com.asy.test.microprofile.person.dto.PersonResponseDto;
import com.asy.test.microprofile.person.mapper.PersonMapper;
import com.asy.test.microprofile.person.model.Person;
import com.asy.test.microprofile.person.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Slf4j
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @Inject
    PersonMapper personMapper;

    public List<PersonResponseDto> getAll() {
        log.info("Retrieving all people");
        List<Person> all = personRepository.findAll();
        System.out.println(all);
        List<PersonResponseDto> prdto = new ArrayList<>();
        for (Person p : all) {
            prdto.add(new PersonResponseDto(p.getId(), p.getName(), p.getEmail(), p.getCreatedAt().toString(), p.getUpdatedAt().toString()));
        }

        PersonResponseDto dto1 = new PersonResponseDto("id", "name", "mail", "12.12.1111", null);
        PersonResponseDto dto2 = new PersonResponseDto("id2", "name", "mail", null, null);
        PersonResponseDto dto3 = new PersonResponseDto("id3", "name", "mail", null, null);
        PersonResponseDto dto4 = new PersonResponseDto("id4", "name", "mail", null, "12.12.3333");
        PersonResponseDto dto5 = new PersonResponseDto("id5", "name", "mail", null, null);
        //return Arrays.asList(dto1, dto2, dto3, dto4, dto5);
        return prdto;
    }


    public void create(PersonDto personDto) {
        log.info("Creating new person");
        Person person = new Person();
        person.setName(personDto.name());
        person.setEmail(personDto.email());
        personRepository.create(person);
    }

    public PersonResponseDto update(PersonDto personDto) {

        throw new WsException(Response.Status.NOT_FOUND.getStatusCode(), "not_found");
    }

    public PersonResponseDto findById(String personId) {

        throw new WsException(Response.Status.NOT_FOUND.getStatusCode(), "not_found");
    }

}
