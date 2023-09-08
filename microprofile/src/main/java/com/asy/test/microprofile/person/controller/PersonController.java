package com.asy.test.microprofile.person.controller;

import com.asy.test.microprofile.person.dto.PersonDto;
import com.asy.test.microprofile.person.dto.PersonResponseDto;
import com.asy.test.microprofile.person.service.PersonService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/person")
@Singleton
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<PersonResponseDto> personResponseDtos = personService.getAll();
        return Response.ok(personResponseDtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String personId) {
        PersonResponseDto responseDto = personService.findById(personId);
        return Response.ok(responseDto).build();

    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(@Valid PersonDto personDto) {
        personService.create(personDto);
        return Response.ok().build();

    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@Valid PersonDto personDto) {
        personService.update(personDto);
        return Response.ok().build();
    }
}
