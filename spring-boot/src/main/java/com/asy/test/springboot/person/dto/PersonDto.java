package com.asy.test.springboot.person.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonDto(
        String id,
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email
) {
}
