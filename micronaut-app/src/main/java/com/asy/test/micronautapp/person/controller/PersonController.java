package com.asy.test.micronautapp.person.controller;

import com.asy.test.micronautapp.person.dto.PersonDto;
import com.asy.test.micronautapp.person.dto.PersonResponseDto;
import com.asy.test.micronautapp.person.service.PersonService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.List;

@Controller("/api/v1/person")
@Validated
public class PersonController {

    @Inject
    private PersonService personService;

    @Get(value = "/list", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<PersonResponseDto>> getAll() {
        return personService.getAll();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getById(String id) {
        return personService.findById(id);
    }

    @Post(value = "/create", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> createPerson(@Body @Valid PersonDto personDto) {
        return personService.create(personDto);
    }

    @Put(value = "/update", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> updatePerson(@Body @Valid PersonDto personDto) {
        return personService.update(personDto);
    }

}
