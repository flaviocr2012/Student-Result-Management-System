package com.shiftLabs.io.Student.Result.Management.System.validator;

import com.shiftLabs.io.Student.Result.Management.System.exceptions.InvalidDateFormatException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.DATE_OF_BIRTH_MUST_BE_VALID;

@Component
public class DateOfBirthValidator {

    public void validateDateOfBirth(LocalDate dateOfBirth) {
        // You can use the dateOfBirth field here to validate its format
        // For example, you can check if it's null or if it matches the pattern

        // For simplicity, let's check if dateOfBirth is in the valid format
        String datePattern = "MM/dd/yyyy";
        String dateOfBirthString = dateOfBirth.format(DateTimeFormatter.ofPattern(datePattern));

        try {
            LocalDate parsedDate = LocalDate.parse(dateOfBirthString, DateTimeFormatter.ofPattern(datePattern));
            if (!parsedDate.equals(dateOfBirth)) {
                throw new InvalidDateFormatException(DATE_OF_BIRTH_MUST_BE_VALID);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(DATE_OF_BIRTH_MUST_BE_VALID);
        }
    }
}

