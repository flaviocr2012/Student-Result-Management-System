package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentResponse> createStudent(
            @RequestBody @Valid StudentRequest studentRequest) {
        var savedStudent = studentService.registerStudent(studentRequest);
        return new ResponseEntity<>(savedStudent.getStatusCode());

    }

}
