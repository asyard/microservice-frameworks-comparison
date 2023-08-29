package com.asy.test.springboot.person.dto;

public record PersonResponseDto(
        String id,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {

}