package com.asy.test.micronautapp.person.dto;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record PersonDto(
        String id,
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email
) {

}
