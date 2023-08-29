package com.asy.test.springboot.person.controller;

import com.asy.test.springboot.person.dto.PersonDto;
import com.asy.test.springboot.person.dto.PersonResponseDto;
import com.asy.test.springboot.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<PersonResponseDto>> getAll() {
        List<PersonResponseDto> personResponseDtos = personService.getAll();
        return ResponseEntity.ok(personResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> getById(@PathVariable("id") String personId) {
        PersonResponseDto responseDto = personService.getById(personId);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/create")
    public ResponseEntity createPerson(@Valid @RequestBody PersonDto personDto) {
        personService.create(personDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updatePerson(@Valid @RequestBody PersonDto personDto) {
        personService.update(personDto);
        return ResponseEntity.ok().build();
    }

}
