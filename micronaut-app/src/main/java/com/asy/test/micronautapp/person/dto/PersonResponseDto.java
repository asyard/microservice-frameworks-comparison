package com.asy.test.micronautapp.person.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PersonResponseDto (
        String id,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {

}