package com.asy.test.springboot.person.service;

import com.asy.test.springboot.person.core.WsException;
import com.asy.test.springboot.person.dto.PersonDto;
import com.asy.test.springboot.person.dto.PersonResponseDto;
import com.asy.test.springboot.person.mapper.PersonMapper;
import com.asy.test.springboot.person.model.Person;
import com.asy.test.springboot.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonResponseDto> getAll() {
        List<Person> personList = personRepository.findAll();
        return personMapper.toDtoList(personList);
    }

    public PersonResponseDto getById(String id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            return personMapper.toDto(personOpt.get());
        }
        throw new WsException(HttpStatus.NOT_FOUND.value(), "not_found");
    }

    public void create(PersonDto personDto) {
        if (personDto.id() != null) {
            throw new WsException(HttpStatus.BAD_REQUEST.value(), "bad_request");
        }

        Person person = new Person();
        person.setName(personDto.name());
        person.setEmail(personDto.email());

        personRepository.save(person);
    }

    public void update(PersonDto personDto) {
        if (personDto.id() == null) {
            throw new WsException(HttpStatus.BAD_REQUEST.value(), "bad_request");
        }
        Optional<Person> personOpt = personRepository.findById(personDto.id());
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setName(personDto.name());
            person.setEmail(personDto.email());
            personRepository.save(person);
            return;
        }
        throw new WsException(HttpStatus.NOT_FOUND.value(), "person_not_found");
    }


}
