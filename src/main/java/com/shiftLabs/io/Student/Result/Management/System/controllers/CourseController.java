package com.shiftLabs.io.Student.Result.Management.System.controllers;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CourseResponse> createCourse(
            @RequestBody @Valid CourseRequest courseRequest){
        var savedCourse = courseService.registerCourse(courseRequest);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        List<CourseResponse> courseList = courseService.getAllCourses();
        return ResponseEntity.ok(courseList);
    }

}
