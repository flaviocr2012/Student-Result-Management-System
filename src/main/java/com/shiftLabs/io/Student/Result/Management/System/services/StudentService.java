package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentResponse registerStudent(StudentRequest studentRequest) {

        if (!isStudentOldEnough(studentRequest.getDateOfBirth())) {
            throw new IllegalArgumentException("Student must be at least 10 years old");
        }

        var student = mapStudent(studentRequest);
        var savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponse.class);

    }

    private boolean isStudentOldEnough(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= 10;
    }

    private StudentResponse mapStudent(StudentRequest studentRequest) {
        return modelMapper.map(studentRequest, StudentResponse.class);
    }
}
