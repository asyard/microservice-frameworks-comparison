package com.asy.test.quarkus.person.dto;

public record PersonResponseDto(
        String id,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {

}