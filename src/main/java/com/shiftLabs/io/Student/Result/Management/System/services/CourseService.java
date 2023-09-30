package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    public CourseResponse registerCourse(CourseRequest courseRequest) {
        var course = mapCourse(courseRequest);
        var savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseResponse.class);
    }
    private CourseResponse mapCourse(CourseRequest courseRequest){
        return modelMapper.map(courseRequest, CourseResponse.class);
    }
}
