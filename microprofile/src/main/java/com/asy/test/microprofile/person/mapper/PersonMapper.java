package com.asy.test.microprofile.person.mapper;

import com.asy.test.microprofile.person.dto.PersonDto;
import com.asy.test.microprofile.person.dto.PersonResponseDto;
import com.asy.test.microprofile.person.model.Person;
import jakarta.json.*;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonMapper {

    public static JsonObject map(PersonResponseDto personResponseDto) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addValue(builder, "id", personResponseDto.id());
        addValue(builder, "name", personResponseDto.name());
        addValue(builder, "email", personResponseDto.email());
        addValue(builder, "createdAt", personResponseDto.createdAt());
        addValue(builder, "updatedAt", personResponseDto.updatedAt());
        return builder.build();
    }

    private static void addValue(JsonObjectBuilder builder, String key, Object value) {
        if (value != null) {
            builder.add(key, value.toString());
        } else {
            builder.addNull(key);
        }
    }

    public static JsonArray map(List<PersonResponseDto> people) {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        people.forEach(p -> {
            JsonObject jsonObject = map(p);
            arrayBuilder.add(jsonObject);
        });
        return arrayBuilder.build();
    }

    public static PersonResponseDto map(InputStream is) {
        try(JsonReader jsonReader = Json.createReader(is)) {
            JsonObject jsonObject = jsonReader.readObject();
            String id = (getStringFromJson("id", jsonObject));
            String name = (getStringFromJson("name", jsonObject));
            String email = (getStringFromJson("email", jsonObject));
            PersonResponseDto personResponseDto = new PersonResponseDto(id, name, email, null, null);
            return personResponseDto;
        }
    }

    private static String getStringFromJson(String key, JsonObject json) {
        String returnedString = null;
        if (json.containsKey(key)) {
            JsonString value = json.getJsonString(key);
            if (value != null) {
                returnedString = value.getString();
            }
        }
        return returnedString;
    }

    private static Integer getIntFromJson(String key, JsonObject json) {
        Integer returnedValue = null;
        if (json.containsKey(key)) {
            JsonNumber value = json.getJsonNumber(key);
            if (value != null) {
                returnedValue = value.intValue();
            }
        }
        return returnedValue;
    }


    protected String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }

        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return formatter.format(localDateTime);

    }
}