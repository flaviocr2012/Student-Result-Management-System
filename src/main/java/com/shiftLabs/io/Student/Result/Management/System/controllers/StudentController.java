package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<StudentResponse> createStudent(
            @RequestBody @Valid StudentRequest studentRequest) {
        var savedStudent = studentService.registerStudent(studentRequest);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        List<StudentResponse> studentsList = studentService.retrieveStudentsList();
        return ResponseEntity.ok(studentsList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long studentId){
        studentService.removeStudent(studentId);
        return new ResponseEntity<>("Student Deleted Successfully !", HttpStatus.OK);
    }

    }


