package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.CourseNotFoundException;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import com.shiftLabs.io.Student.Result.Management.System.models.Result;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.COURSE_NOT_FOUND;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final ResultRepository resultRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, ResultRepository resultRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.resultRepository = resultRepository;
        this.modelMapper = modelMapper;
    }

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
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            courseRepository.delete(course);

            List<Result> resultsToDelete = resultRepository.findByCourse(course);
            resultRepository.deleteAll(resultsToDelete);

        } else {
            throw new CourseNotFoundException(COURSE_NOT_FOUND + courseId);
        }
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
            throw new CourseNotFoundException(
                    COURSE_NOT_FOUND + courseId);
        }
    }

    private Course mapCourse(CourseRequest courseRequest) {
        return modelMapper.map(courseRequest, Course.class);
    }

}
