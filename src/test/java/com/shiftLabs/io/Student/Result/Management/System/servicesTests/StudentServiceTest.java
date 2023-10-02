package com.shiftLabs.io.Student.Result.Management.System.servicesTests;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.helpers.StudentHelper;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.StudentRepository;
import com.shiftLabs.io.Student.Result.Management.System.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ResultRepository resultRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private StudentHelper studentHelper;
    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldRegisterStudent() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("John");
        studentRequest.setDateOfBirth(LocalDate.of(2000, 1, 1));
        studentRequest.setEmailAddress("john@example.com");


        when(studentHelper.isStudentOldEnough(any(LocalDate.class)))
                .thenReturn(true);
        when(studentRepository.save(isNull())).thenReturn(new Student());
        when(modelMapper.map(any(Student.class), eq(StudentResponse.class)))
                .thenReturn(new StudentResponse());
        StudentResponse response = studentService.registerStudent(studentRequest);

        assertNotNull(response);
        verify(studentRepository).save(isNull());
    }

    @Test
    public void testRetrieveStudentsList() {
        List<Student> sampleStudents = Arrays.asList(
                new Student(1L, "John", LocalDate.of(2000, 1, 1), "john@example.com"),
                new Student(2L, "Alice", LocalDate.of(1999, 5, 15), "alice@example.com")
        );

        when(studentRepository.findAll()).thenReturn(sampleStudents);
        when(modelMapper.map(any(Student.class), eq(StudentResponse.class)))
                .thenReturn(new StudentResponse());

        List<StudentResponse> responseList = studentService.retrieveStudentsList();

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
    }

    @Test
    public void testRemoveStudent() {
        Student mockStudent = new Student(1L, "John", LocalDate.of(2000, 1, 1), "john@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(mockStudent));
        when(resultRepository.findByStudent(mockStudent)).thenReturn(new ArrayList<>());

        studentService.removeStudent(1L);

        verify(resultRepository, times(1)).deleteAll(anyList());
        verify(studentRepository, times(1)).delete(mockStudent);
    }
}
