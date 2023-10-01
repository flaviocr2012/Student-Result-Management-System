package com.shiftLabs.io.Student.Result.Management.System.helpers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class StudentHelper {

    private final ModelMapper modelMapper;

    @Autowired
    public StudentHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public boolean isStudentOldEnough(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= 10;
    }

    public Student mapStudent(StudentRequest studentRequest) {
        return modelMapper.map(studentRequest, Student.class);
    }

}
