package com.asy.test.microprofile.person.dto;


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
        public PersonDto(String name, String email) {
                this(null, name, email);
        }

}
