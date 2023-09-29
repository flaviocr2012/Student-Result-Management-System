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

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    public ResponseEntity<StudentResponse> registerStudent(StudentRequest studentRequest) {
        return Optional.ofNullable(studentRequest)
                .map(this::mapStudent)
                .map(studentRepository::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

    }

    private StudentResponse mapStudent(StudentRequest studentRequest) {
        return modelMapper.map(studentRequest, StudentResponse.class);
    }
}
