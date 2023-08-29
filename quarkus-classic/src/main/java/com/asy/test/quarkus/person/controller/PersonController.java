package com.asy.test.quarkus.person.controller;


import com.asy.test.quarkus.person.dto.PersonDto;
import com.asy.test.quarkus.person.dto.PersonResponseDto;
import com.asy.test.quarkus.person.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1/person")
@ApplicationScoped
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonResponseDto> getAll() {
        List<PersonResponseDto> personResponseDtos = personService.getAll();
        return personResponseDtos;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponseDto getById(@PathParam("id") String personId) {
        PersonResponseDto responseDto = personService.findById(personId);
        return responseDto;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponseDto createPerson(@Valid PersonDto personDto) {
        return personService.create(personDto);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponseDto updatePerson(@Valid PersonDto personDto) {
        return personService.update(personDto);
    }
}
