package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import com.shiftLabs.io.Student.Result.Management.System.models.Result;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final ResultRepository resultRepository;

    private final ModelMapper modelMapper;

    public CourseResponse registerCourse(CourseRequest courseRequest) {
        Course course = mapCourse(courseRequest);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseResponse.class);
    }

    public List<CourseResponse> retrieveCourseslist() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());
    }

    public void removeCourse(Long courseId) {
        Optional<Course> studentOptional = courseRepository.findById(courseId);
        if (studentOptional.isPresent()) {
            Course course = studentOptional.get();
            courseRepository.delete(course);
        } else throw new EntityNotFoundException("Course not found with ID: " + courseId);
    }

    public CourseResponse updateCourse(Long courseId, CourseRequest updatedCourse) {
        Optional<Course> existingCourseOptional = courseRepository.findById(courseId);

        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            existingCourse.setCourseName(updatedCourse.getCourseName());
            Course savedCourse = courseRepository.save(existingCourse);

            List<Result> resultsToUpdate = resultRepository.findByCourse(existingCourse);
            resultsToUpdate.forEach(result -> result.setCourse(savedCourse));
            resultRepository.saveAll(resultsToUpdate);

            return modelMapper.map(savedCourse, CourseResponse.class);
        } else {
            throw new EntityNotFoundException("Course not found with id: " + courseId);
        }
    }

    private Course mapCourse(CourseRequest courseRequest) {
        return modelMapper.map(courseRequest, Course.class);
    }


}
