package com.asy.test.springboot.person.mapper;

import com.asy.test.springboot.person.dto.PersonResponseDto;
import com.asy.test.springboot.person.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {


    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatTimestamp")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatTimestamp")
    public abstract PersonResponseDto toDto(Person person);

    public abstract List<PersonResponseDto> toDtoList(List<Person> personList);

    @Named("formatTimestamp")
    protected String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }

        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return formatter.format(localDateTime);

    }

}
