package com.asy.test.microprofile.person.dto;

public record PersonResponseDto(
        String id,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {

}