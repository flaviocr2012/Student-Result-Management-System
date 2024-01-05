package com.shiftLabs.io.Student.Result.Management.System.configs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.InvalidDateFormatException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.DATE_OF_BIRTH_MUST_BE_VALID;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, IOException {
        String dateString = jsonParser.getText();
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            throw new InvalidDateFormatException(DATE_OF_BIRTH_MUST_BE_VALID);
        }
    }
}
