package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    public CourseResponse registerCourse(CourseRequest courseRequest) {
        var course = mapCourse(courseRequest);
        Course savedCourse = (Course) courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseResponse.class);
    }
    private CourseResponse mapCourse(CourseRequest courseRequest){
        return modelMapper.map(courseRequest, CourseResponse.class);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());
    }
}
