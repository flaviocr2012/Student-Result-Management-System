package com.shiftLabs.io.Student.Result.Management.System.servicesTests;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.CourseRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.CourseResponse;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.CourseNotFoundException;
import com.shiftLabs.io.Student.Result.Management.System.models.Course;
import com.shiftLabs.io.Student.Result.Management.System.repositories.CourseRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import com.shiftLabs.io.Student.Result.Management.System.services.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseService courseService;


    @Test
    void shouldRegisterCourse() {

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName("Java");

        when(courseRepository.save(isNull())).thenReturn(new Course());

        when(modelMapper.map(any(Course.class), eq(CourseResponse.class)))
                .thenReturn(new CourseResponse());

        CourseResponse response = courseService.registerCourse(courseRequest);

        assertNotNull(response);

        verify(courseRepository).save(isNull());
    }


    @Test
    void shouldRetrieveCoursesList() {
        List<Course> sampleCourses = List.of(
                new Course(1L, "Java"),
                new Course(2L, "Python")
        );

        when(courseRepository.findAll()).thenReturn(sampleCourses);
        when(modelMapper.map(any(Course.class), eq(CourseResponse.class)))
                .thenReturn(new CourseResponse());

        List<CourseResponse> responseList = courseService.retrieveCourseslist();

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
    }

    @Test
    void shouldRemoveCourse() {
        Course mockCourse = new Course(1L, "Java");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(mockCourse));
        when(resultRepository.findByCourse(mockCourse)).thenReturn(new ArrayList<>());

        courseService.removeCourse(1L);

        verify(resultRepository, times(1)).deleteAll(anyList());
        verify(courseRepository, times(1)).delete(mockCourse);
    }

    @Test
    void shouldRemoveCourseNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> {
            courseService.removeCourse(1L);
        });
    }
}
