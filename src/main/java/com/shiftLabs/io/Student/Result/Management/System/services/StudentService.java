package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentResponse registerStudent(StudentRequest studentRequest) {
        var student = mapStudent(studentRequest);
        var savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponse.class);

    }

    private StudentResponse mapStudent(StudentRequest studentRequest) {
        return modelMapper.map(studentRequest, StudentResponse.class);
    }
}
