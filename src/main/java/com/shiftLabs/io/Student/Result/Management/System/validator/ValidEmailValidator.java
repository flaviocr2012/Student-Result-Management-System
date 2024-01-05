package com.shiftLabs.io.Student.Result.Management.System.validator;


import com.shiftLabs.io.Student.Result.Management.System.helpers.EmailValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EmailValidator.isValidEmail(email);
    }
}

